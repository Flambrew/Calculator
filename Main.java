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

            String function = args.length > 0 ? args[i] : "3!";
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
                System.out.println(out.toString());
    
            } catch (IllegalTokenException e) {
                e.printStackTrace();
            } catch (IllegalSyntaxException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
    }
}