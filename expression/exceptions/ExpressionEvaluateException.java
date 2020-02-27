package expression.exceptions;

public class ExpressionEvaluateException extends RuntimeException {
    public ExpressionEvaluateException(String msg) {
        super("Error occured: " + msg);
    }
}