package src._main;

import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import src.exceptions.CalculatorException;

public class Main {
    public static void main(String... args) {
        Lexer lx = new Lexer();
        Parser pr = new Parser();
        Interpreter in = new Interpreter();

        DecimalFormat df = new DecimalFormat("#.#");

        try {
            String function = args.length > 0 ? args[0] : JOptionPane.showInputDialog("Input equation:");
            JOptionPane.showMessageDialog(null, String.format("%s = %s", function,
                    df.format(in.calculate(pr.parse(lx.createTokens(function))).VALUE)));

            // System.out.printf("%s = %s", function,
            // df.format(in.calculate(pr.parse(lx.createTokens(function))).VALUE));

        } catch (CalculatorException e) {
            JOptionPane.showMessageDialog(null, e.ERROR_MESSAGE);
            e.printStackTrace();
            main();
        }
    }
}