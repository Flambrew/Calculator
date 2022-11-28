package src.nodes;

import src.tokens.TT;
import src.tokens.Token;

public class NumberNode extends Node {

    public final Double VALUE;
    private final Token VARIABLE;

    public NumberNode(Token token) {
        super(null);
        if (token.isA(TT.NUM)) {
            this.VALUE = token.VALUE;
            this.VARIABLE = null;
        } else {
            this.VARIABLE = token;
            this.VALUE = null;
        }
    }

    public Node[] parts() {
        return null;
    }

    public NumberNode clone() {
        return VARIABLE == null ? new NumberNode(new Token(TT.NUM, VALUE)) : new NumberNode(VARIABLE);
    }

    public String toString() {
        return VARIABLE == null ? VALUE.toString() : VARIABLE.toString().substring(4);
    }
}