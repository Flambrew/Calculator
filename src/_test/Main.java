package src._test;

import java.util.Arrays;

import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;
import src.nodes.Node;
import src.processing.Lexer;
import src.processing.Parser;
import src.tokens.Token;

public class Main {
    public static void main(String[] args) {

        String function = "4 * 3 + -sin(3) + 2";
        Lexer lx = null;
        Token[] tokens = null;
        Parser pr = null;
        Node ast = null;

        try {

            System.out.println(function);

            lx = new Lexer(function);
            tokens = lx.createTokens();
            System.out.println(Arrays.deepToString(tokens));

            // ---

            pr = new Parser(tokens);
            ast = pr.parse();
            System.out.println(ast);

        } catch (IllegalTokenException e) {
            e.printStackTrace();
        } catch (IllegalSyntaxException e) {
            e.printStackTrace();
        }
    }
}