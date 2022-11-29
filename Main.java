import java.util.Arrays;

import src.nodes.Node;
import src.nodes.NumberNode;
import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;

import src.exceptions.IllegalOperationException;
import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;

public class Main {
    public static void main(String[] args) {
        Lexer lx = new Lexer();
        Parser pr = new Parser();
        Interpreter in = new Interpreter();

        try {
            for (int i = 0; i < args.length || i < 1; i++) {

                String function = args.length > 0 ? args[i] : "1 + 2 - abs(3 * 4! / sin[5 ^ cos{6}]) % -tan(7)";
                System.out.println(function);
                System.out.printf("%f", in.calculate(pr.parse(lx.createTokens(function))).VALUE);
                
            }
        } catch (IllegalTokenException e) {
            e.printStackTrace();
        } catch (IllegalSyntaxException e) {
            e.printStackTrace();
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
    }
}