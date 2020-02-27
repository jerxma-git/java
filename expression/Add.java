package expression;
public class Add extends AbstractBinOperation {
    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    // @Override
    // public int evaluate(int val) {
    //     return first.evaluate(val) + second.evaluate(val);
    // }

    // @Override
    // public double evaluate(double val) {
    //     return first.evaluate(val) + second.evaluate(val);
    // }


    @Override
    protected int calculate(int first, int second) {
        return first + second;
    }

    // @Override
    // protected double calculate(double first, double second) {
    //     return first + second;
    // }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    

    

}