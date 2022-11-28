package src.processing;

import src.nodes.FunctionNode;
import src.nodes.Node;
import src.nodes.NumberNode;
import src.nodes.OperationNode;
import src.tokens.TT;

public class Interpreter {

    public NumberNode calculate(Node node, Variable... givenValues) {
        
        if (node instanceof NumberNode) return new NumberNode(TT.NUM, node.VALUE);
        
        while (!(node.parts()[0] instanceof NumberNode)) { // until NODE has been converted to a number

            node = calculate(node.parts()[0].clone()); // recursion your way up down until the node is a number

        }

        if (node instanceof OperationNode) {
            
        }

        if (node instanceof FunctionNode) {
            
        }

        return null;
    }

    private int getVarsCount() {
        return 0;
    }
}
