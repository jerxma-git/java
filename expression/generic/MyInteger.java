package expression.generic;

public class MyInteger implements Evaluative<MyInteger> {
    private int val;

    public MyInteger(int val) {
        this.val = val;
    }

    @Override
    public MyInteger add(MyInteger a) {
        if (a instanceof MyInteger) {
            return new MyInteger(val + ((MyInteger) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyInteger sub(MyInteger a) {
        if (a instanceof MyInteger) {
            return new MyInteger(val - ((MyInteger) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyInteger mul(MyInteger a) {
        if (a instanceof MyInteger) {
            return new MyInteger(val * ((MyInteger) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyInteger div(MyInteger a) {
        if (a instanceof MyInteger) {
            return new MyInteger(val / ((MyInteger) a).val);
        }
        throw new RuntimeException();
    }

    @Override
    public MyInteger neg() {
        return new MyInteger(-val);
    }
    
    @Override
    public MyInteger parse(String val) {
        return new MyInteger(Integer.parseInt(val));
    } 

}