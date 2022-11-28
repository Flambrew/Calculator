package src.nodes;

import src.tokens.Token;

public class NumberNode extends Node {
    
    public final Double VALUE;

    public NumberNode(Token token) {
        this.VALUE = token.VALUE;
    }

    public String toString() {
        return VALUE.toString();
    }
}