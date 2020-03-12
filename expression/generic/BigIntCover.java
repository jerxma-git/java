package expression.generic;

import java.math.BigInteger;

public class BigIntCover implements Evaluative<BigIntCover> {
    private BigInteger val;

    public BigIntCover(BigInteger val) {
        this.val = val;
    }

    public BigInteger getVal() {
        return val;
    }

    public BigIntCover add(BigIntCover cover) {
        return new BigIntCover(val.add(((BigIntCover) cover).getVal()));
    }

    public BigIntCover div(BigIntCover cover) {
        return new BigIntCover(val.divide(((BigIntCover) cover).getVal()));
    }

    public BigIntCover sub(BigIntCover cover) {
        return new BigIntCover(val.subtract(((BigIntCover) cover).getVal()));
    }

    public BigIntCover mul(BigIntCover cover) {
        return new BigIntCover(val.multiply(((BigIntCover) cover).getVal()));
    }

    public BigIntCover neg() {
        return new BigIntCover(val.negate());
    }

    public BigIntCover parse(String str) {
        return new BigIntCover(new BigInteger(str));
    }


}