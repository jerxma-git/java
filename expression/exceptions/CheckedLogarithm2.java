package expression.exceptions;

import expression.CommonExpression;
import expression.Logarithm2;

public class CheckedLogarithm2 extends Logarithm2 {
    public CheckedLogarithm2(CommonExpression expression) {
        super(expression);
    }

    @Override
    public int calculate(int val) {
        if (val < 1) {
            throw new UnsupportedArgumentException("log2 " + val);
        }
        return super.calculate(val);
    }
}