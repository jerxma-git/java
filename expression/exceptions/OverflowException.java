package expression.exceptions;

public class OverflowException extends ExpressionEvaluateException {
    public OverflowException(String msg) {
        super("Overflow in: " + msg);
    }
}