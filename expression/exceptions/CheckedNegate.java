package expression.exceptions;

import expression.CommonExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(CommonExpression expression) {
        super(expression);
    }

    protected int calculate(int val) {
        if (val == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return super.calculate(val);
    }


}