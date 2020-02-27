package expression.exceptions;

public class UnderflowException extends ExpressionEvaluateException {
    public UnderflowException(String msg) {
        super("Underflow in: "+ msg);
    }
}