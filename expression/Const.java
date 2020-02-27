package expression;
public class Const implements CommonExpression {

    private Number val;

    public Const(Number val) {
        this.val = val;
    }

    
    
    @Override
    public boolean equals(Object obj) {
        return  obj instanceof Const
                && ((Const) obj).val.equals(this.val);
    }

    @Override
    public int evaluate(int val) {
        return this.val.intValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return this.val.intValue();
    }

    // @Override
    // public double evaluate(double val) {
    //     return this.val.doubleValue();
    // }

    public boolean isNegative() {
        return val.doubleValue() < 0;
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