package src.processing;

import src.nodes.FunctionNode;
import src.nodes.Node;
import src.nodes.NumberNode;
import src.nodes.OperationNode;

public class Interpreter {

    public NumberNode calculate(Node node, Variable... givenValues) {

        if (node instanceof NumberNode)
            return new NumberNode(node.VALUE);

        while (!(node.parts()[0] instanceof NumberNode || node.parts()[1] instanceof NumberNode)) {

            node = node instanceof OperationNode ? calculate(node.parts()[0].clone()) : calculate(node.parts()[1].clone());

        }

        if (node instanceof OperationNode) {
            double operandA = node.parts()[0].VALUE;
            double operandB = calculate(node.parts()[2]).VALUE;
            switch (node.parts()[1].OPERATOR) {
                case ADD:
                    return new NumberNode(operandA + operandB);
                case SUB:
                    return new NumberNode(operandA - operandB);
                case MUL:
                    return new NumberNode(operandA * operandB);
                case DIV:
                    return new NumberNode(operandA / operandB);
                case MOD:
                    return new NumberNode(operandA % operandB);
                case POW:
                    return new NumberNode(Math.pow(operandA, operandB));
                default:
                    return null;
            }
        }

        if (node instanceof FunctionNode) {
            double operand = node.parts()[1].VALUE;
            if (node.parts()[0].OPERATOR != null) {
                switch (node.parts()[0].OPERATOR) {
                    case SIN:
                        return new NumberNode(Math.sin(operand));
                    case COS:
                        return new NumberNode(Math.cos(operand));
                    case TAN:
                        return new NumberNode(Math.tan(operand));
                    case CSC:
                        return new NumberNode(1 / Math.sin(operand));
                    case SEC:
                        return new NumberNode(1 / Math.cos(operand));
                    case COT:
                        return new NumberNode(1 / Math.tan(operand));
                    case ABS:
                        return new NumberNode(Math.abs(operand));
                    case LOG:
                        return new NumberNode(Math.log10(operand));
                    case EXP:
                        return new NumberNode(Math.exp(operand));
                    case FAC:
                        if (Math.abs(operand - (int) operand) > 0.000001)
                            return null;
                        double out = 1;
                        for (int i = 1; i <= operand; i++)
                            out *= i;
                        return new NumberNode(out);
                    default:
                        return null;
                }
            }
            return new NumberNode(operand);
        }

        return null;
    }
}
