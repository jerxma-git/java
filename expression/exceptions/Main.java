package expression.exceptions;

import expression.*;
public class Main {
    public static void main(String[] args) throws ParserException  {
        // System.out.println(new CheckedDivide(new Const(Integer.MIN_VALUE), new Const(-1)).evaluate(0));
        Parser parser = new ExpressionParser();
        CommonExpression exp = new ExpressionParser().parse("(- -1914842190) / y // z");
        // CommonExpression exp = new ExpressionParser().parse("-  - 2");
        System.out.println(exp);
        System.out.println(exp.evaluate(-510870105, 1949412994, 441981722));
        System.out.println("done");

        
    }
}