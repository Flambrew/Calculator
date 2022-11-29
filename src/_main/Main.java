package src._main;

import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;

import java.util.Arrays;

import javax.swing.JOptionPane;

import src.exceptions.IllegalOperationException;
import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;

public class Main {
    public static void main(String[] args) {
        Lexer lx = new Lexer();
        Parser pr = new Parser();
        Interpreter in = new Interpreter();

        try {

            String function = args.length > 0 ? args[0] : JOptionPane.showInputDialog("Input equation:");
            function = !function.equals("") ? function : "1 + 2 - abs(3 * 4! / sin[5 ^ cos{6}]) % -tan(7)";

            System.out.println(function);
            System.out.println(Arrays.deepToString(lx.createTokens(function)));
            System.out.println(pr.parse(lx.createTokens(function)));

            System.out.printf("%s = %f", function, in.calculate(pr.parse(lx.createTokens(function))).VALUE);

        } catch (IllegalTokenException e) {
            e.printStackTrace();
        } catch (IllegalSyntaxException e) {
            e.printStackTrace();
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
    }
}