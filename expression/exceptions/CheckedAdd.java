package expression.exceptions;

import expression.CommonExpression;
import expression.Add;

public class CheckedAdd extends Add {
    public CheckedAdd(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    protected int calculate(int first, int second) throws OverflowException {
        if (first > 0 && Integer.MAX_VALUE - first < second) {
            throw new OverflowException("overflow");
        }
        if (first < 0 && Integer.MIN_VALUE - first > second) {
            throw new UnderflowException("underflow");
        }
        return super.calculate(first, second);
    }
}