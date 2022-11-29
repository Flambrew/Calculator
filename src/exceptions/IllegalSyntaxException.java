package src.exceptions;

public class IllegalSyntaxException extends CalculatorException {

    public enum SyntaxErrors {
        MISMATCHED_PARENS("Mismatched parens."),
        FUNCTION_PARAMETER_UNDEFINED("Parameter for %s undefined."),
        MISSING_NUMERIC_TOKEN("Missing token of type: (PREFIX|VALUE|LPAREN)"),
        MISSING_OPERATIONAL_TOKEN("Missing token of type: (SUFFIX|OPERATION|RPAREN)");

        public final String ERROR_MESSAGE;

        SyntaxErrors(String errorMessage) {
            this.ERROR_MESSAGE = errorMessage;
        }
    }

    public IllegalSyntaxException(SyntaxErrors error) {
        super(error.ERROR_MESSAGE);
    }

    public IllegalSyntaxException(SyntaxErrors error, String source) {
        super(String.format(error.ERROR_MESSAGE,
                error.ERROR_MESSAGE.matches("^([^%]|(%%))*%s([^%]|(%%))*$") ? source : null));
    }
}