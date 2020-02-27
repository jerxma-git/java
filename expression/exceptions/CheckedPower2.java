package expression.exceptions;

import expression.CommonExpression;
import expression.Power2;

public class CheckedPower2 extends Power2 {
    public CheckedPower2(CommonExpression expression) {
        super(expression);
    }

    @Override
    public int calculate(int val) {
        if (val < 0) {
            throw new UnsupportedArgumentException("pow2 " + val); 
        }
        if (val > Integer.SIZE - 1) {
            throw new OverflowException("pow2 " + val);
        }
        // if (overflow 2^val)
        return super.calculate(val);
    }
}