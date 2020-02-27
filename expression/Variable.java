package expression;
public class Variable implements CommonExpression {
    String varName;
    public Variable(String varName) {
        this.varName = varName;
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Variable && ((Variable) obj).varName.equals(this.varName);
    }

    @Override
    public int evaluate(int val) {
        return val;
    }

    // @Override
    // public double evaluate(double val) {
    //     return val;
    // }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (varName) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException("Variable '" + varName + 
                        "' is not supported. Failed to evaluate");
        }
    }

    @Override
    public String toString() {
        return varName;
    }


    @Override
    public int hashCode() {
        return varName.hashCode();
    }
}