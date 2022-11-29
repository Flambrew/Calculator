package src.processing;

import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalSyntaxException.SyntaxErrors;
import src.nodes.OperationNode;
import src.nodes.FunctionNode;
import src.nodes.NumberNode;
import src.nodes.Node;

import src.tokens.TGroup;
import src.tokens.Token;
import src.tokens.TT;

public class Parser {

    Token[] tokens;
    Token currentToken;
    Integer position;

    public Node parse(Token[] tokens) throws IllegalSyntaxException {
        this.tokens = tokens;
        this.position = -1;
        advance();
        return expression();
    }

    private Node factor() throws IllegalSyntaxException {
        Token left = currentToken;
        if (left != null) {
            if (left.isA(TGroup.PREFIX)) {
                advance();
                if (currentToken.isA(TT.LPAREN)) {
                    advance();
                    Node right = expression();
                    if (currentToken.isA(TT.RPAREN)) {
                        advance();
                        if (currentToken != null && currentToken.isA(TGroup.SUFFIX)) {
                            Token function = currentToken;
                            advance();
                            return new FunctionNode(function, new FunctionNode(left, right));
                        }
                        return new FunctionNode(left, right);
                    }
                }
                throw new IllegalSyntaxException(SyntaxErrors.FUNCTION_PARAMETER_UNDEFINED, left.toString());
            } else if (currentToken.isA(TT.LPAREN)) {
                advance();
                Node value = expression();
                if (currentToken.isA(TT.RPAREN)) {
                    advance();
                    if (currentToken != null && currentToken.isA(TGroup.SUFFIX)) {
                        Token function = currentToken;
                        advance();
                        return new FunctionNode(function, new FunctionNode(value));
                    }
                    return value;
                }
                throw new IllegalSyntaxException(SyntaxErrors.FUNCTION_PARAMETER_UNDEFINED, left.toString());
            } else if (currentToken.isA(TT.SUB)) {
                advance();
                if (currentToken.isA(TGroup.PREFIX, TGroup.VALUE) || currentToken.isA(TT.LPAREN)) {
                    if (currentToken != null && currentToken.isA(TGroup.SUFFIX)) {
                        Token function = currentToken;
                        advance();
                        return new FunctionNode(function,
                                new OperationNode(new NumberNode(new Token(TT.NUM, 0)), new Token(TT.SUB), factor()));
                    }
                    return new OperationNode(new NumberNode(new Token(TT.NUM, 0)), new Token(TT.SUB), factor());
                }
                throw new IllegalSyntaxException(SyntaxErrors.MISSING_NUMERIC_TOKEN);
            } else if (currentToken.isA(TGroup.VALUE)) {
                advance();
                if (currentToken != null && currentToken.isA(TGroup.SUFFIX)) {
                    Token function = currentToken;
                    advance();
                    return new FunctionNode(function, new NumberNode(left));
                }
                return new NumberNode(left);
            }
        }
        throw new IllegalSyntaxException(SyntaxErrors.MISSING_NUMERIC_TOKEN);
    }

    private Node expo() throws IllegalSyntaxException {
        Node left = factor();
        while (currentToken != null && currentToken.isA(TT.POW)) {
            Token opToken = currentToken;
            advance();
            Node right = factor();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX) || currentToken.isA(TT.RPAREN)))
        throw new IllegalSyntaxException(SyntaxErrors.MISSING_OPERATIONAL_TOKEN);
        return left;
    }

    private Node term() throws IllegalSyntaxException {
        Node left = expo();
        while (currentToken != null && currentToken.isA(TT.MUL, TT.DIV, TT.MOD)) {
            Token opToken = currentToken;
            advance();
            Node right = expo();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX) || currentToken.isA(TT.RPAREN)))
        throw new IllegalSyntaxException(SyntaxErrors.MISSING_OPERATIONAL_TOKEN);
        return left;
    }

    private Node expression() throws IllegalSyntaxException {
        Node left = term();
        while (currentToken != null && currentToken.isA(TT.ADD, TT.SUB)) {
            Token opToken = currentToken;
            advance();
            Node right = term();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX) || currentToken.isA(TT.RPAREN)))
        throw new IllegalSyntaxException(SyntaxErrors.MISSING_OPERATIONAL_TOKEN);
        return left;
    }

    private void advance() {
        position++;
        currentToken = position < tokens.length ? tokens[position] : null;
    }
}