package expression.generic;

public abstract class AbstractUnOperation<T extends Evaluative<T>> implements CommonExpression<T> {
    protected CommonExpression<T> CommonExpression;
    protected int hash;
    public AbstractUnOperation(CommonExpression<T> CommonExpression) {
        this.CommonExpression = CommonExpression;
        hash = 57 * (CommonExpression.hashCode() + 991) + (getClass().hashCode() + 277) * 3;
    }

    @Override
    public int hashCode() {
        return hash;
    } 

    protected abstract T calculate(T val);
    public abstract String getSign();



    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(CommonExpression.evaluate(x,y,z));
    }

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