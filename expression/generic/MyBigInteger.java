package expression.generic;

import java.math.BigInteger;

public class MyBigInteger implements Evaluative<MyBigInteger> {
    private BigInteger val;

    public MyBigInteger(BigInteger val) {
        this.val = val;
    }

    @Override
    public MyBigInteger add(MyBigInteger a) {
        if (a instanceof MyBigInteger) {
            return new MyBigInteger(val.add(((MyBigInteger) a).val));
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyBigInteger sub(MyBigInteger a) {
        if (a instanceof MyBigInteger) {
            return new MyBigInteger(val.subtract(((MyBigInteger) a).val));
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyBigInteger mul(MyBigInteger a) {
        if (a instanceof MyBigInteger) {
            return new MyBigInteger(val.multiply(((MyBigInteger) a).val));
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyBigInteger div(MyBigInteger a) {
        if (a instanceof MyBigInteger) {
            return new MyBigInteger(val.divide(((MyBigInteger) a).val));
        }
        throw new RuntimeException();
    }

    @Override
    public MyBigInteger neg() {
        return new MyBigInteger(val.negate());
    }
    
    @Override
    public MyBigInteger parse(String val) {
        return new MyBigInteger(new BigInteger(val));
    } 

}