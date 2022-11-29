package src.exceptions;

public class CalculatorException extends Exception {

    public final String ERROR_MESSAGE;

    public CalculatorException(String error) {
        super(error);
        this.ERROR_MESSAGE = error;
    }
}