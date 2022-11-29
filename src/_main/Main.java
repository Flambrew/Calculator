package src._main;

import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import src.exceptions.IllegalOperationException;
import src.exceptions.IllegalSyntaxException;
import src.exceptions.IllegalTokenException;

public class Main {
    public static void main(String[] args) {
        Lexer lx = new Lexer();
        Parser pr = new Parser();
        Interpreter in = new Interpreter();

        DecimalFormat df = new DecimalFormat("#.#");

        try {
            String function = args.length > 0 ? args[0] : JOptionPane.showInputDialog("Input equation:");
            JOptionPane.showMessageDialog(null,
                    String.format("%s = %s", function, df.format(in.calculate(pr.parse(lx.createTokens(function))).VALUE)));

            System.out.printf("%s = %s", function, df.format(in.calculate(pr.parse(lx.createTokens(function))).VALUE));

        } catch (IllegalTokenException e) {
            e.printStackTrace();
        } catch (IllegalSyntaxException e) {
            e.printStackTrace();
        } catch (IllegalOperationException e) {
            e.printStackTrace();
        }
    }
}