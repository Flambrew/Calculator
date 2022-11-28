

import java.util.Arrays;

import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;
import src.nodes.Node;
import src.processing.Lexer;
import src.processing.Parser;
import src.tokens.Token;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {

            System.out.println();

            String function = args[i];
            Lexer lx = null;
            Token[] tokens = null;
            Parser pr = null;
            Node ast = null;
            String abstractSyntaxTree = null;
    
            try {

                System.out.println(function);
    
                lx = new Lexer(function);
                tokens = lx.createTokens();
                System.out.println(Arrays.deepToString(tokens));

    
                pr = new Parser(tokens);
                ast = pr.parse();
                abstractSyntaxTree = ast.toString();
                System.out.println(abstractSyntaxTree);
    
            } catch (IllegalTokenException e) {
                e.printStackTrace();
            } catch (IllegalSyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}