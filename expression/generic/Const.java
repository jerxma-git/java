package expression.generic;
public class Const<T extends Evaluative<T>> implements CommonExpression<T> {

    private T val;

    public Const(T val) {
        this.val = val;
    }
    
    
    @Override
    public T evaluate(T x, T y, T z) {
        return val;
    }

    // @Override
    // public double evaluate(double val) {
    //     return this.val.doubleValue();
    // }

    public boolean isNegative() {
        // return val.doubleValue() < 0;
        return false;
    }


    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public int hashCode() {
        return val.hashCode();
    }
}