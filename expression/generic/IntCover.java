package expression.generic;


public class IntCover implements Evaluative<IntCover> {
    private int val;

    public IntCover(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public IntCover add(IntCover cover) {
        return new IntCover(val + ((IntCover) cover).getVal());
    }

    public IntCover div(IntCover cover) {
        return new IntCover(val + ((IntCover) cover).getVal());
    }

    public IntCover sub(IntCover cover) {
        return new IntCover(val - ((IntCover) cover).getVal());
    }

    public IntCover mul(IntCover cover) {
        return new IntCover(val * ((IntCover) cover).getVal());
    }

    public IntCover neg() {
        return new IntCover(-val);
    }

    @Override
    public IntCover parse(String str) {
        return new IntCover(Integer.parseInt(str));
    }


}