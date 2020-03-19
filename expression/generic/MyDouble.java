package expression.generic;

public class MyDouble implements Evaluative<MyDouble> {
    private double val;

    public MyDouble(double val) {
        this.val = val;
    }

    @Override
    public MyDouble add(MyDouble a) {
        if (a instanceof MyDouble) {
            return new MyDouble(val + ((MyDouble) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyDouble sub(MyDouble a) {
        if (a instanceof MyDouble) {
            return new MyDouble(val - ((MyDouble) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyDouble mul(MyDouble a) {
        if (a instanceof MyDouble) {
            return new MyDouble(val * ((MyDouble) a).val);
        }
        throw new RuntimeException();
    }
    
    @Override
    public MyDouble div(MyDouble a) {
        if (a instanceof MyDouble) {
            return new MyDouble(val / ((MyDouble) a).val);
        }
        throw new RuntimeException();
    }

    @Override
    public MyDouble neg() {
        return new MyDouble(-val);
    }
    
    @Override
    public MyDouble parse(String val) {
        return new MyDouble(Double.parseDouble(val));
    } 

}