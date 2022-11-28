package src.nodes;

import src.tokens.Token;

public class OperatorNode extends Node {

    private final Token OPERATOR;

    public OperatorNode(Token token) {
        super(null);
        this.OPERATOR = token;
    }

    public Node[] parts() {
        return null;
    }

    public Node clone() {
        return null;
    }

    public String toString() {
        return null;
    }

}
