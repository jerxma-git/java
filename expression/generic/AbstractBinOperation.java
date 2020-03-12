package expression.generic;
public abstract class AbstractBinOperation<T extends Evaluative> implements CommonExpression<T> {
    protected CommonExpression<T> first;
    protected CommonExpression<T> second;
    protected int hash;
    protected AbstractBinOperation(CommonExpression<T> first, CommonExpression<T> second) {
        this.first = first;
        this.second = second;
        hash = 17 * (first.hashCode() + 191 * second.hashCode()) + getClass().hashCode();
    }

    protected abstract boolean isCommutative();

    protected abstract int getPriority();

    @Override
    public String toString() {
        return "(" + first.toString() + " " + getSign() + " " + second.toString() + ")";
    }

    @Override
    public int hashCode() {
        return hash;
    }

    protected abstract T calculate(T first, T second);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(first.evaluate(x, y, z), second.evaluate(x, y, z)); 
    }

    protected abstract String getSign();



    // private void appendCommonExpression(StringBuilder str, boolean checkCommutativity, CommonExpression op) {
    //     boolean brackets = bracketsNeeded(op, checkCommutativity);
    //     if (brackets) {
    //         str.append('(');
    //     }
    //     str.append(op.toMiniString());
    //     if (brackets) {
    //         str.append(')');
    //     }
    // }

    private String placeInBrackets(CommonExpression<T> op, boolean checkCommutativity) {
        boolean brackets = bracketsNeeded(op, checkCommutativity);
        return (brackets ? "(" : "") + op.toMiniString() + (brackets ? ")" : "");
    }

    private boolean bracketsNeeded(CommonExpression<T> op, boolean checkCommutativity) {
        if (op instanceof AbstractBinOperation) {
            AbstractBinOperation<T> binOp = (AbstractBinOperation<T>) op;
            return checkPriority(binOp) 
                    || checkCommutativity && checkCommutativity(binOp);
        }
        return false;
    }

    private boolean checkPriority(AbstractBinOperation<T> binOp) {
        return binOp.getPriority() < this.getPriority();
    }

    private boolean checkCommutativity(AbstractBinOperation<T> binOp) {
        return !(binOp.isCommutative() && this.isCommutative()) 
                && binOp.getPriority() <= this.getPriority();
        
    } 

    @Override
    public String toMiniString() {
        return placeInBrackets(first, false) + " " + getSign() + " " + placeInBrackets(second, true);
        // StringBuilder tmpString = new StringBuilder();
        
        // appendCommonExpression(tmpString, false, first);

        // tmpString.append(' ')
        //         .append(getSign())
        //         .append(' ');
        
        // appendCommonExpression(tmpString, true, second);
        
        // return tmpString.toString();
    }

}