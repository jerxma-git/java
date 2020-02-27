package expression;

public abstract class AbstractUnOperation implements CommonExpression {
    protected CommonExpression CommonExpression;
    protected int hash;
    public AbstractUnOperation(CommonExpression CommonExpression) {
        this.CommonExpression = CommonExpression;
        hash = 57 * (CommonExpression.hashCode() + 991) + (getClass().hashCode() + 277) * 3;
    }

    @Override
    public int hashCode() {
        return hash;
    } 

    protected abstract int calculate(int val);
    // protected abstract double calculate(double val);
    public abstract String getSign();

    @Override
    public int evaluate(int val) {
        return calculate(CommonExpression.evaluate(val));
    }



    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(CommonExpression.evaluate(x,y,z));
    }

    // @Override
    // public double evaluate(double val) {
    //     return calculate(val);
    // }

    @Override
    public String toString() {
        return getSign() + "(" + CommonExpression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        boolean brackets = !(CommonExpression instanceof Const 
            && !((Const) CommonExpression).isNegative() || CommonExpression instanceof Variable);
        return getSign() + (brackets ? "(" : "") + CommonExpression.toMiniString() + (brackets ? ")" : "");
    }





}