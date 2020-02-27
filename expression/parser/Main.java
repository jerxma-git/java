package expression.parser;

import expression.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("start");
        CommonExpression b = new ExpressionParser().parse("2 + 3 + log2 (x*2 * pow2 3)");
        System.out.println(b.toString());
        System.out.println(b.evaluate(0, 0, 0));
    }
}