package expression;

public class Logarithm extends AbstractBinOperation {
    
    public Logarithm(CommonExpression base, CommonExpression expression) {
        super(base, expression);
    }

    @Override
    public String getSign() {
        return "//";
        // space?
    }

    @Override
    public int calculate(int first, int second) {
        int res = 0;
        while (first >= second) {
            first /= second;
            res++;
        }
        return res;
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
