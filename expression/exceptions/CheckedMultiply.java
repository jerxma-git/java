package expression.exceptions;

import expression.CommonExpression;
import expression.Multiply;

public class CheckedMultiply extends Multiply {
    CheckedMultiply(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int first, int second) throws OverflowException, UnderflowException {
        checkEvaluation(first, second);
        return super.calculate(first, second);
    }

    public static void checkEvaluation(int first, int second) {
        if (first == Integer.MIN_VALUE && second == -1 
                || second == Integer.MIN_VALUE && first == -1 
                || first < 0 && second < 0 && Integer.MAX_VALUE / second > first
                || first > 0 && second > 0 && Integer.MAX_VALUE / second < first) {
            throw new OverflowException(first + " * " + second);
        }
        if (first < 0 && second > 0 && Integer.MIN_VALUE / second > first 
                || first > 0 && second < -1 && Integer.MIN_VALUE / second < first) {
            throw new UnderflowException(first + " * " + second); // ?
        }
    }
}