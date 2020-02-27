package expression.parser;

import java.util.*;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {
    private Operator currOperator = Operator.CLOSE_BRACKET;
    private static final int highestLevel = 3;
    private static final int lowestLevel = 0;
    
    private static final Map<Operator, Integer> PRIORITIES = new HashMap<>(Map.of(
            Operator.CLOSE_BRACKET, highestLevel + 1,
            Operator.MUL, 1,
            Operator.DIV, 1,
            Operator.ADD, 2,
            Operator.SUB, 2,
            Operator.RIGHT_SHIFT, 3,
            Operator.LEFT_SHIFT, 3
    ));

    private static final Map<String, Operator> OPERATORS = new HashMap<>(Map.of(
            "*", Operator.MUL,
            "/", Operator.DIV,
            "+", Operator.ADD,
            "-", Operator.SUB,
            ">>", Operator.RIGHT_SHIFT,
            "<<", Operator.LEFT_SHIFT,
            ")", Operator.CLOSE_BRACKET
    ));

    


    private static final Map<Character, String> CHAR_TO_STRING = new HashMap<>(Map.of(
            '*', "*",
            '/', "/",
            '+', "+",
            '-', "-",
            '<', "<<",
            '>', ">>",
            ')', ")"
    ));

    @Override
    public CommonExpression parse(String expression) {

        setSource(new StringSource(format(expression)));
        nextChar();
        return getExpression(highestLevel);
    }


    private CommonExpression getExpression(int level) {
        if (level == lowestLevel) {
            return getPrimitiveExpression();
        }
        CommonExpression expression = getExpression(level - 1);
        while (PRIORITIES.get(currOperator) == level) {
            expression = makeExpression(currOperator, expression, getExpression(level - 1));
        }
        if (level == highestLevel) {
            testOperator();
        }
        return expression;
    }

    private Const getConst(boolean isNegative) {
        StringBuilder val = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            val.append(curr);
            // System.out.println("read " + curr + " as const");
            nextChar();

        }
        testOperator();

        try {
            return new Const(Integer.parseInt(val.toString()));
        } catch (NumberFormatException e) {
            throw error("Illegal constant: '" + val.toString() + "'");
        }
    }

    private Variable getVariable() {
        StringBuilder var = new StringBuilder();
        while (!testOperator()) {
            // System.out.println("read " + curr + " as var");
            var.append(curr);
            nextChar();
        }
        return new Variable(var.toString());
    }
    private CommonExpression getPrimitiveExpression() {
        if (test('-')) {
            if (between('0', '9')) {
                return getConst(true);
            } else {
                return Negate.getCompressedNegate(getExpression(lowestLevel));
            }
        } else if (test('(')) {
            return getExpression(highestLevel);
        } else if (between('0', '9')) {
            return getConst(false);
        } else {
            return getVariable();
        }
    }

    private boolean testOperator() {
        if (CHAR_TO_STRING.containsKey(curr)) {
            getOperator();
            return true;
        }
        return false;
    }

    private void getOperator() {
        String operator = CHAR_TO_STRING.get(curr);
        expect(operator);
        // System.out.println("read " + operator + " as operator");

        currOperator = OPERATORS.get(operator);
        // System.out.println("new operator: " + currOperator);
    }

    private CommonExpression makeExpression(Operator operator, CommonExpression first, CommonExpression second) {
        switch (operator) {
            case ADD:
                return new Add(first, second);
            case MUL:
                return new Multiply(first, second);
            case SUB:
                return new Subtract(first, second);
            case DIV:
                return new Divide(first, second);
            case RIGHT_SHIFT:
                return new RightShift(first, second);
            case LEFT_SHIFT:
                return new LeftShift(first, second);
            default:
                throw new IllegalStateException("Unsupported operator" + operator);
        }
    }


    private static String format(String str) {
        StringBuilder formattedString = new StringBuilder();
        for (char ch : str.toCharArray()) {
            if (!Character.isWhitespace(ch)) {
                formattedString.append(ch);
            }
        }
        return formattedString.toString() + ")";
    }
}