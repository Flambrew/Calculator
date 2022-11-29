package src.nodes;

import src.tokens.TT;
import src.tokens.Token;

public class FunctionNode extends Node {

    private Node EXPRESSION;

    public FunctionNode(Node expression) {
        this.EXPRESSION = expression;
    }

    public FunctionNode(Token function, Node expression) {
        super(function.TOKEN_TYPE);
        this.EXPRESSION = expression;
    }

    public FunctionNode(TT function, Node expression) {
        super(function);
        this.EXPRESSION = expression;
    }

    public Node[] parts() {
        return OPERATOR != null ? new Node[] {new OperatorNode(new Token(OPERATOR)), EXPRESSION} : new Node[] {EXPRESSION};
    }

    public FunctionNode clone() {
        return OPERATOR != null ? new FunctionNode(OPERATOR, EXPRESSION.clone()) : new FunctionNode(EXPRESSION.clone());
    }

    public String toString() {
        return OPERATOR != null ? String.format("(%s, %s)", OPERATOR, EXPRESSION) : EXPRESSION.toString();
    }
}