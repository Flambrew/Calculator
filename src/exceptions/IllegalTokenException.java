package src.exceptions;

public class IllegalTokenException extends CalculatorException {

    public IllegalTokenException(String character) {
        super(String.format("Token \"%s\" is invalid.", character));
    }
}