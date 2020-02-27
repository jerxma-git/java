package expression.exceptions;

public class UnsupportedArgumentException extends ExpressionEvaluateException {
    public UnsupportedArgumentException(String msg) {
        super("This argument is not supported: " + msg);
    }
}