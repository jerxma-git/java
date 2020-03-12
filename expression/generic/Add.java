package expression.generic;
public class Add<T extends Evaluative> extends AbstractBinOperation<T> {
    public Add(CommonExpression<T> first, CommonExpression<T> second) {
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
    protected T calculate(T first, T second) {
        return (T) first.add(second);
    }


    @Override
    protected boolean isCommutative() {
        return true;
    }

    @Override
    protected int getPriority() {
        return 0;
    }

    

    

}