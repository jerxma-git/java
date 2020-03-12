package expression.generic;


public class DoubleCover implements Evaluative<DoubleCover> {
    private double val;

    public DoubleCover(double val) {
        this.val = val;
    }

    public double getVal() {
        return val;
    }

    public DoubleCover add(DoubleCover cover) {
        return new DoubleCover(val + ((DoubleCover) cover).getVal());
    }

    public DoubleCover div(DoubleCover cover) {
        return new DoubleCover(val + ((DoubleCover) cover).getVal());
    }

    public DoubleCover sub(DoubleCover cover) {
        return new DoubleCover(val - ((DoubleCover) cover).getVal());
    }

    public DoubleCover mul(DoubleCover cover) {
        return new DoubleCover(val * ((DoubleCover) cover).getVal());
    }

    public DoubleCover neg() {
        return new DoubleCover(-val);
    }

    public DoubleCover parse(String str) {
        return new DoubleCover(Double.parseDouble(str));
    }


}