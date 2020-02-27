package expression;
public abstract class AbstractBinOperation implements CommonExpression {
    protected CommonExpression first;
    protected CommonExpression second;
    protected int hash;
    protected AbstractBinOperation(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
        hash = 17 * (first.hashCode() + 191 * second.hashCode()) + getClass().hashCode();
    }

    protected abstract boolean isCommutative();

    protected abstract int getPriority();

    @Override
    public String toString() {
        return "(" + first.toString() + " " + getSign() + " " + second.toString() + ")";
        // return new StringBuilder()
        //         .append('(')
        //         .append(first.toString())
        //         .append(' ')
        //         .append(this.getSign())
        //         .append(' ')
        //         .append(second.toString())
        //         .append(')').toString();
    }

    @Override 
    public boolean equals(Object obj) {
        return obj instanceof AbstractBinOperation
                && (((AbstractBinOperation) obj).first.equals(this.first)) 
                && (((AbstractBinOperation) obj).second.equals(this.second))
                && obj.getClass() == this.getClass(); //????????????????????????????????????????????????????????????????????????????????/
    }

    @Override
    public int hashCode() {
        return hash;
    }

    protected abstract int calculate(int first, int second);
    // protected abstract double calculate(double first, double second);

    // @Override
    // public double evaluate(double val) {
    //     return calculate(first.evaluate(val), second.evaluate(val));
    // }
    @Override
    public int evaluate(int val) {
        return calculate(first.evaluate(val), second.evaluate(val));
    }

    @Override
    public int evaluate(int x, int y, int z) {
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

    private String placeInBrackets(CommonExpression op, boolean checkCommutativity) {
        boolean brackets = bracketsNeeded(op, checkCommutativity);
        return (brackets ? "(" : "") + op.toMiniString() + (brackets ? ")" : "");
    }

    private boolean bracketsNeeded(CommonExpression op, boolean checkCommutativity) {
        if (op instanceof AbstractBinOperation) {
            AbstractBinOperation binOp = (AbstractBinOperation) op;
            return checkPriority(binOp) 
                    || checkCommutativity && checkCommutativity(binOp);
        }
        return false;
    }

    private boolean checkPriority(AbstractBinOperation binOp) {
        return binOp.getPriority() < this.getPriority();
    }

    private boolean checkCommutativity(AbstractBinOperation binOp) {
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