package src.exceptions;

public class IllegalOperationException extends CalculatorException {

    public IllegalOperationException(String error) {
        super(String.format("Cannot compute %s.", error));
    }
}