package expression.exceptions;

import expression.CommonExpression;
import expression.Logarithm;

public class CheckedLogarithm extends Logarithm {
    public CheckedLogarithm(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    public int calculate(int first, int second) {
        if (first < 1 || second <= 1) {
            throw new UnsupportedArgumentException(first + " // " + second);
        }
        return super.calculate(first, second);
    }
}