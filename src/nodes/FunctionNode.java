package src.nodes;

import src.tokens.Token;
public class FunctionNode extends Node {

    public final Token FUNCTION;
    public final Node EXPRESSION;

    public FunctionNode(Node expression) {
        this.FUNCTION = null;
        this.EXPRESSION = expression;
    }

    public FunctionNode(Token function, Node expression) {
        this.FUNCTION = function;
        this.EXPRESSION = expression;
    }

    public String toString() {
        return FUNCTION != null ? String.format("(%s, %s)", FUNCTION, EXPRESSION) : EXPRESSION.toString();
    }
}
