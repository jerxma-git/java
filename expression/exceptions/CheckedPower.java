package expression.exceptions;

import expression.CommonExpression;
import expression.Power;

public class CheckedPower extends Power {
    public CheckedPower(CommonExpression base, CommonExpression expression) {
        super(base, expression);
    }

    @Override
    public int calculate(int first, int second) throws ExpressionEvaluateException {
        if (second < 0 || second == 0 && first == 0) {
            throw new UnsupportedArgumentException(first + " ** " + second); 
        }
        int r = 1;
        while (second > 0) {
            if (second % 2 == 1) {
                CheckedMultiply.checkEvaluation(r, first);
                r *= first;
            }
            second /= 2;
            if (second != 0) {
                CheckedMultiply.checkEvaluation(first, first);
            }
            first *= first;
        }
        return r;
    }
}