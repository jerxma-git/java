package expression.generic;

import expression.exceptions.CheckedNegate;

public class Negate<T extends Evaluative> extends AbstractUnOperation<T> {

    public Negate(CommonExpression<T> CommonExpression) {
        super(CommonExpression);
    }

    @Override
    public String getSign() {
        return "-";
    }    

    @Override
    protected T calculate(T val) {
        return (T) val.neg();
    }

    // @Override
    // public double calculate(double val) {
    //     return -1 * val;
    // }


    // public static CommonExpression getCompressedNegate(CommonExpression CommonExpression) {
    //     if (CommonExpression instanceof Const) {
    //         return new Const(-CommonExpression.evaluate(0));
    //     } else if (CommonExpression instanceof Negate) {
    //         return ((Negate) CommonExpression).CommonExpression;
    //     } else {
    //         return new CheckedNegate(CommonExpression);
    //     }
    // }

    
}