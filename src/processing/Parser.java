package src.processing;

import src.exceptions.IllegalSyntaxException;

import src.tokens.*;
import src.nodes.*;

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

    private Node factor() throws IllegalSyntaxException { // TODO implement variables
        Token left = currentToken;
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
            throw new IllegalSyntaxException(String.format("Parameter for %s unspecified. (check parens)", left));
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
                return new FunctionNode(value);
            }
            throw new IllegalSyntaxException(String.format("Parameter for %s unspecified. (check parens)", left));
        } else if (currentToken.isA(TT.NUM, TT.SUB)) {
            if (currentToken.isA(TT.SUB)) {
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
                throw new IllegalSyntaxException("Missing token of type: [PREFIX|NUM|LPAREN]");
            }
            left = currentToken;
            advance();
            if (currentToken != null && currentToken.isA(TGroup.SUFFIX)) {
                Token function = currentToken;
                advance();
                return new FunctionNode(function, new NumberNode(left));
            }
            return new NumberNode(left);
        }
        throw new IllegalSyntaxException("Missing token of type: [PREFIX|NUM|LPAREN]");
    }

    private Node expo() throws IllegalSyntaxException {
        Node left = factor();
        while (currentToken != null && currentToken.isA(TT.POW)) {
            Token opToken = currentToken;
            advance();
            Node right = factor();
            left = new OperationNode(left, opToken, right);
        }
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX)
                || currentToken.isA(TT.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|SUFFIX|RPAREN]");
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
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX)
                || currentToken.isA(TT.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|SUFFIX|RPAREN]");
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
        if (!(currentToken == null || currentToken.isA(TGroup.OPERATION, TGroup.SUFFIX)
                || currentToken.isA(TT.RPAREN)))
            throw new IllegalSyntaxException("Missing token of type: [OPERATION|SUFFIX|RPAREN]");
        return left;
    }
}