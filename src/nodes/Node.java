package src.nodes;

import src.tokens.TT;
import src.tokens.Token;

public class Node {

    public final Double VALUE;
    public final Token VARIABLE;
    public final TT OPERATOR;

    public Node() {
        this.VALUE = null;
        this.VARIABLE = null;
        this.OPERATOR = null;
    }

    public Node(Double value, Token variable) {
        this.VALUE = value;
        this.VARIABLE = variable;
        this.OPERATOR = null;
    }

    public Node(TT operator) {
        this.VALUE = null;
        this.VARIABLE = null;
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