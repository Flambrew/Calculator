package src.nodes;

import src.tokens.Token;

public class OperatorNode extends Node {

    public final Token OPERATOR;

    public OperatorNode(Token token) {
        super(null, token.TOKEN_TYPE);
        this.OPERATOR = token;
    }

    public Node[] parts() {
        return null;
    }

    public Node clone() {
        return new OperatorNode(OPERATOR);
    }

    public String toString() {
        return OPERATOR.toString();
    }

}
