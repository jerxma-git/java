package expression.exceptions;

import expression.Divide;
import expression.CommonExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    protected int calculate(int first, int second) throws DivisionByZeroException, OverflowException {
        if (second == 0) {
            throw new DivisionByZeroException("division by zero");
        }
        if (first == Integer.MIN_VALUE && second == -1) {
            throw new OverflowException("overflow");
        }
        return super.calculate(first, second);
    }
}