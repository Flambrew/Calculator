package src.nodes;

import src.tokens.TT;

public class Node {

    public final Double VALUE;
    public final TT OPERATOR;

    public Node() {
        this.VALUE = null;
        this.OPERATOR = null;
    }

    public Node(Double value, TT operator) {
        this.VALUE = value;
        this.OPERATOR = operator;
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