package expression;

public class Power extends AbstractBinOperation {
    public Power(CommonExpression base, CommonExpression expression) {
        super(base, expression);
    }

    @Override
    public String getSign() {
        return "**";
    }

    @Override
    public int calculate(int first, int second) {
        int r = 1;
        while (second != 0) {
            if (second % 2 == 1) {
                r *= first;
            }
            second /= 2;
            first *= first;
        }
        return r;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }
}