package src.processing;

import src.exceptions.IllegalSyntaxException;
import src.nodes.OperationNode;
import src.nodes.FunctionNode;
import src.nodes.Node;
import src.nodes.NumberNode;
import src.tokens.Group;
import src.tokens.Token;
import src.tokens.TokenType;

public class Parser {

    Integer position;

    Token[] tokens;
    Token currentToken;

    public Parser(Token[] tokens) {
        this.tokens = tokens;
        this.position = -1;
        this.currentToken = null;
        advance();
    }

    private void advance() {
        position++;
        currentToken = position < tokens.length ? tokens[position] : null;
    }

    public Node parse() throws IllegalSyntaxException {
        return expression();
    }

    // TODO implement variables
    private Node factor() throws IllegalSyntaxException {
        Token left = currentToken;
        if (left.isA(Group.PRE_FUNC)) {
            advance();
            if (currentToken.isA(TokenType.LPAREN)) {
                advance();
                Node right = expression();
                if (currentToken.isA(TokenType.RPAREN)) {
                    advance();
                    return new FunctionNode(left, right);
                }
            }
            throw new IllegalSyntaxException(String.format("Parameter for %s unspecified. (check parens)", left));
        } else if (currentToken.isA(TokenType.LPAREN)) {
            advance();
            Node value = expression();
            if (currentToken.isA(TokenType.RPAREN)) {
                advance();
                return new FunctionNode(value);
            }
            throw new IllegalSyntaxException(String.format("Parameter for %s unspecified. (check parens)", left));
        } else if (currentToken.isA(TokenType.NUM, TokenType.SUB)) {
            if (currentToken.isA(TokenType.SUB)) {
                advance();
                if (currentToken.isA(Group.PRE_FUNC, Group.VALUE) || currentToken.isA(TokenType.LPAREN)) {
                    return new OperationNode(new NumberNode(new Token(TokenType.NUM, 0)),
                            new Token(TokenType.SUB), factor());
                }
                throw new IllegalSyntaxException("Missing token of type: [PRE_FUNC|NUM|LPAREN]");
            }
            left = currentToken;
            advance();
            return new NumberNode(left);
        }
        throw new IllegalSyntaxException("Missing token of type: [PRE_FUNC|NUM|LPAREN]");
    }

    private Node expo() throws IllegalSyntaxException {
        Node left = factor();
        while (currentToken != null && currentToken.isA(TokenType.POW)) {
            Token opToken = currentToken;
            advance();
            Node right = factor();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(Group.OPERATION, Group.POST_FUNC)
                || currentToken.isA(TokenType.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|POST_FUNC|RPAREN]");
        return left;
    }

    private Node term() throws IllegalSyntaxException {
        Node left = expo();
        while (currentToken != null && currentToken.isA(TokenType.MUL, TokenType.DIV, TokenType.MOD)) {
            Token opToken = currentToken;
            advance();
            Node right = expo();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(Group.OPERATION, Group.POST_FUNC)
                || currentToken.isA(TokenType.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|POST_FUNC|RPAREN]");
        return left;
    }

    private Node expression() throws IllegalSyntaxException {
        Node left = term();
        while (currentToken != null && currentToken.isA(TokenType.ADD, TokenType.SUB)) {
            Token opToken = currentToken;
            advance();
            Node right = term();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(Group.OPERATION, Group.POST_FUNC)
                || currentToken.isA(TokenType.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|POST_FUNC|RPAREN]");
        return left;
    }
}

// figure this shit out tomorrow morning
