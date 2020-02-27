package expression;

public class Logarithm2 extends AbstractUnOperation {
    
    public Logarithm2(CommonExpression expression) {
        super(expression);
    }

    @Override
    public String getSign() {
        return "log2 ";
        // space?
    }

    @Override
    public int calculate(int val) {
        int res = 0;
        while (val > 1) {
            val /= 2;
            res++;
        }
        return res;
    }

    

}