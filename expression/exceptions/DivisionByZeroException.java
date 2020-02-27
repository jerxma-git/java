package expression.exceptions;


public class DivisionByZeroException extends ExpressionEvaluateException {
    DivisionByZeroException(String msg) {
        super("Division by zero in: " + msg);
    }
}