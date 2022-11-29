package src.exceptions;

public class IllegalArgumentException extends CalculatorException {

    public IllegalArgumentException() {
        super("Input cannot be null/empty.");
    }
}