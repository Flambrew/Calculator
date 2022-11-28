package src.nodes;

import src.tokens.Token;
import src.tokens.TT;

public class OperationNode extends Node {
    
    public final Node LEFT_NODE, RIGHT_NODE;
    public final Token OP_TOKEN;

    public OperationNode(Node leftNode, Token opToken, Node rightNode) {
        this.LEFT_NODE = leftNode;
        this.OP_TOKEN = opToken;
        this.RIGHT_NODE = rightNode;
    }

    public OperationNode(double leftNodeValue, TT opToken, Node rightNode) {
        this.LEFT_NODE = new NumberNode(new Token(TT.NUM, leftNodeValue));
        this.OP_TOKEN = new Token(opToken);
        this.RIGHT_NODE = rightNode;
    }

    public String toString() {
        return String.format("(%s, %s, %s)", LEFT_NODE, OP_TOKEN, RIGHT_NODE);
    }
}
