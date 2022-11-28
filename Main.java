import java.util.Arrays;

import src.nodes.Node;
import src.nodes.NumberNode;
import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;
import src.tokens.Token;

import src.exceptions.*;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < args.length || i < 1; i++) {

            // "1 + 2 - abs(3 * 4! / sin[5 ^ cos{6}]) % -tan(7)"

            String function = args.length > 0 ? args[i] : "1 + 2 - abs(3 * 4! / sin[5 ^ cos{6}]) % -tan(7)";
            Lexer lx = new Lexer();
            Parser pr = new Parser();
            Interpreter in = new Interpreter();
    
            try {

                System.out.println(function);
    
                Token [] tokens = lx.createTokens(function);
                System.out.println(Arrays.deepToString(tokens));
    
                Node ast = pr.parse(tokens);
                System.out.println(ast.toString());

                NumberNode out = in.calculate(ast);
                System.out.printf("%f", out.VALUE);
    
            } catch (IllegalTokenException e) {
                e.printStackTrace();
            } catch (IllegalSyntaxException e) {
                e.printStackTrace();
            } catch (IllegalOperationException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
    }
}