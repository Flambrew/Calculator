package src.nodes;

import src.tokens.Token;

public class FunctionNode extends Node {

    private final Node FUNCTION, EXPRESSION;

    public FunctionNode(Node expression) {
        super(null);
        this.FUNCTION = null;
        this.EXPRESSION = expression;
    }

    public FunctionNode(Token function, Node expression) {
        super(null);
        this.FUNCTION = new OperatorNode(function);
        this.EXPRESSION = expression;
    }

    public FunctionNode(Node function, Node expression) {
        super(null);
        this.FUNCTION = function;
        this.EXPRESSION = expression;
    }

    public Node[] parts() {
        return FUNCTION != null ? new Node[] {FUNCTION, EXPRESSION} : new Node[] {EXPRESSION};
    }

    public FunctionNode clone() {
        return FUNCTION != null ? new FunctionNode(FUNCTION, EXPRESSION.clone()) : new FunctionNode(EXPRESSION.clone());
    }

    public String toString() {
        return FUNCTION != null ? String.format("(%s, %s)", FUNCTION, EXPRESSION) : EXPRESSION.toString();
    }
}