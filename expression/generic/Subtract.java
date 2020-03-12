package expression.generic;
public class Subtract<T extends Evaluative> extends AbstractBinOperation<T> {
    public Subtract(CommonExpression<T> first, CommonExpression<T> second) {
        super(first, second);
    }

    @Override
    protected String getSign() {
        return "-";
    }

    

    // @Override
    // public int evaluate(int val) {
    //     return first.evaluate(val) - second.evaluate(val);
    // }

    // @Override
    // public double evaluate(double val) {
    //     return first.evaluate(val) - second.evaluate(val);
    // }

    @Override
    protected T calculate(T first, T second) {
        return (T) first.sub(second);
    }

    // @Override
    // protected double calculate(double first, double second) {
    //     return first - second;
    // }

    @Override
    protected boolean isCommutative() {
        return false;
    }

    @Override
    protected int getPriority() {
        return 0;
    }

}