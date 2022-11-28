package src.nodes;

import src.tokens.TT;
import src.tokens.Token;

public class NumberNode extends Node {

    private final Token VARIABLE;

    public NumberNode(Token token) {
        super(token.isA(TT.NUM) ? token.VALUE : null, null);
        this.VARIABLE = token.isA(TT.NUM) ? null : token;
    }

    public NumberNode(Double value) {
        super(value, null);
        this.VARIABLE = null;
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