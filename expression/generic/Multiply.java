package expression.generic;
public class Multiply<T extends Evaluative<T>> extends AbstractBinOperation<T> {
    public Multiply(CommonExpression<T> first, CommonExpression<T> second) {
        super(first, second);
    }

    @Override
    protected String getSign() {
        return "*";
    }

    // @Override
    // public int evaluate(int val) {
    //     return first.evaluate(val) * second.evaluate(val);
    // }

    // @Override
    // public double evaluate(double val) {
    //     return first.evaluate(val) * second.evaluate(val);
    // }

    @Override
    protected T calculate(T first, T second) {
        return (T) first.mul(second);
    }

    // @Override
    // protected double calculate(double first, double second) {
    //     return first * second;
    // }

    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    protected int getPriority() {
        return 1;
    }
}