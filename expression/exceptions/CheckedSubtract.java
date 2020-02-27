package expression.exceptions;

import expression.CommonExpression;
import expression.Subtract;;

public class CheckedSubtract extends Subtract {
    CheckedSubtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    protected int calculate(int first, int second) throws OverflowException {
        if (second < 0 && Integer.MAX_VALUE + second < first) {
            throw new OverflowException("overflow");
        }
        if (second > 0 && Integer.MIN_VALUE + second > first) {
            throw new UnderflowException("underflow");
        }
        return super.calculate(first, second);
    }
}