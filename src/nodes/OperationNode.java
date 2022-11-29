package src.nodes;

import src.tokens.Token;

public class OperationNode extends Node {

    private Node LEFT_NODE, OP_TOKEN, RIGHT_NODE;

    public OperationNode(Node leftNode, Token opToken, Node rightNode) {
        this.LEFT_NODE = leftNode;
        this.OP_TOKEN = new OperatorNode(opToken);
        this.RIGHT_NODE = rightNode;
    }

    public OperationNode(Node leftNode, Node opToken, Node rightNode) {
        this.LEFT_NODE = leftNode;
        this.OP_TOKEN = opToken;
        this.RIGHT_NODE = rightNode;
    }

    public Node[] parts() {
        return new Node[] {LEFT_NODE, OP_TOKEN, RIGHT_NODE};
    }

    public OperationNode clone() {
        return new OperationNode(LEFT_NODE.clone(), OP_TOKEN, RIGHT_NODE.clone());
    }

    public String toString() {
        return String.format("(%s, %s, %s)", LEFT_NODE, OP_TOKEN, RIGHT_NODE);
    }
}