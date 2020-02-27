package expression;

import expression.exceptions.CheckedNegate;

public class Negate extends AbstractUnOperation {

    public Negate(CommonExpression CommonExpression) {
        super(CommonExpression);
    }

    @Override
    public String getSign() {
        return "-";
    }    

    @Override
    protected int calculate(int val) {
        return -1 * val;
    }

    // @Override
    // public double calculate(double val) {
    //     return -1 * val;
    // }


    public static CommonExpression getCompressedNegate(CommonExpression CommonExpression) {
        if (CommonExpression instanceof Const) {
            return new Const(-CommonExpression.evaluate(0));
        } else if (CommonExpression instanceof Negate) {
            return ((Negate) CommonExpression).CommonExpression;
        } else {
            return new CheckedNegate(CommonExpression);
        }
    }

    
}