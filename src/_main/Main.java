package src._main;

import src.processing.Interpreter;
import src.processing.Lexer;
import src.processing.Parser;
import src.processing.Variable;

import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JOptionPane;

import src.exceptions.CalculatorException;

public class Main {
    public static void main(String... args) {
        args = new String[] { };
        Lexer lx = new Lexer();
        Parser pr = new Parser();
        Interpreter in = new Interpreter();

        DecimalFormat df = new DecimalFormat("#.######");

        Variable[] vars = args.length == 0 ? new Variable[args.length] : new Variable[args.length - 1];

        for (int i = 0; i < vars.length; i++)
            vars[i] = new Variable(args[i + 1].substring(0, args[i + 1].indexOf("=")),
                    Integer.parseInt(args[i + 1].substring(args[i + 1].indexOf("=") + 1)));

        // add variable requests loop until done, cancel to move on to calculations
        try {
            String function = args.length > 0 ? args[0] : JOptionPane.showInputDialog("Input equation:");

            System.out.println(Arrays.deepToString(lx.createTokens(function)));
            System.out.println(pr.parse(lx.createTokens(function)));
            System.out.println(in.calculate(pr.parse(lx.createTokens(function)), vars));

            JOptionPane.showMessageDialog(null, String.format("%s = %s", function, df.format(in.calculate(pr.parse(lx.createTokens(function)), vars).VALUE)));
        } catch (CalculatorException e) {
            JOptionPane.showMessageDialog(null, e.ERROR_MESSAGE);
            e.printStackTrace();
            main(args);
        }
    }
}