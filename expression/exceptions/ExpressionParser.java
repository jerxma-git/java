package expression.exceptions;

import java.util.*;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {
    private enum Token {
        VAR, CONST, BIN_OP, CLOSE_BRACKET, OPEN_BRACKET, UN_OP
    }
    private static final int highestLevel = 3;
    private static final int lowestLevel = -1;
    private static final Map<Operator, Integer> priorities = Map.of(
            Operator.CLOSE_BRACKET, highestLevel + 1,
            Operator.MUL, 1,
            Operator.DIV, 1,
            Operator.ADD, 2,
            Operator.SUB, 2,
            Operator.RIGHT_SHIFT, 3,
            Operator.LEFT_SHIFT, 3,
            Operator.POWER, 0,
            Operator.LOGARITHM, 0

    );
    private static final Map<String, Operator> binOperators = Map.of(
            "*", Operator.MUL,
            "/", Operator.DIV,
            "+", Operator.ADD,
            "-", Operator.SUB,
            ">>", Operator.RIGHT_SHIFT,
            "<<", Operator.LEFT_SHIFT,
            "**", Operator.POWER,
            "//", Operator.LOGARITHM,
            ")", Operator.CLOSE_BRACKET
    );
    private static final Map<String, Operator> unOperators = Map.of(
        "-", Operator.NEG,
        "pow2", Operator.POW,
        "log2", Operator.LOG
    );
    private static final Map<Character, String> charToString = Map.of(
            '*', "*",
            '/', "/",
            '+', "+",
            '-', "-",
            '<', "<<",
            '>', ">>",
            ')', ")",
            'l', "log2",
            'p', "pow2"
    );

    private Operator lastOperator;
    private Operator lastUnOperator;
    private Token lastToken;
    private Token nextToken;
    private boolean negateKostil;
    
    @Override
    public CommonExpression parse(String expression) throws ParserException {
        lastToken = Token.OPEN_BRACKET;
        lastOperator = Operator.CLOSE_BRACKET;
        lastUnOperator = Operator.NULL_OP;
        negateKostil = false;
        setSource(new StringSource(expression + ')'));
        nextChar();
        skipWhiteSpaces();
        CommonExpression parsedExpression = getExpression(highestLevel);
        if (hasNext()) {
            throw error("Invalid input: excess close bracket");
        }
        return parsedExpression;
    }

    private CommonExpression getExpression(int level) throws ParserException {
        if (level == lowestLevel) {
            return getPrimeExpression();
        }
        CommonExpression expression = getExpression(level - 1);
        while (priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, getExpression(level - 1));
        }
        if (level == highestLevel && lastOperator != Operator.CLOSE_BRACKET) {
            throw error("Parenthesis mismatch");
        }
        return expression;
    }

    private Const getConst(boolean isNegative) throws ParserException {
        negateKostil = isNegative;
        StringBuilder val = new StringBuilder(isNegative ? "-" : "");
        while (between('0', '9')) {
            val.append(curr);
            nextChar();
        }
        lastToken = Token.CONST;
        skipWhiteSpaces();
        testOperator();
        try {
            return new Const(Integer.parseInt(val.toString()));
        } catch (NumberFormatException e) {
            throw new OverflowException(val.toString());
        }
    }

    private Variable getVariable() throws ParserException {
        StringBuilder var = new StringBuilder();
        // is varName a string or a char?
        while (isVarSym()) {
            var.append(curr);
            nextChar();
        }
        lastToken = Token.VAR;
        skipWhiteSpaces();
        testOperator();
        return new Variable(var.toString());
    }

    

    private CommonExpression getPrimeExpression() throws ParserException {
        String unOperatorStr = null;
        Operator currUnOperator = Operator.NULL_OP;
        CommonExpression primeExpression = null;
        if (unOpFirstChar()) {
            unOperatorStr = charToString.get(curr);
            currUnOperator = unOperators.get(unOperatorStr);
            expect(unOperatorStr);
            if (isVarSym() && currUnOperator != Operator.NEG) {
                throw error("Expected space, got " + curr);
            } ///tf?
            nextToken = Token.UN_OP;
            checkNextToken();
            lastToken = Token.UN_OP;
            skipWhiteSpaces();         
        } else if (test('(')) {
            nextToken = Token.OPEN_BRACKET;
            checkNextToken();
            lastToken = Token.OPEN_BRACKET;
            primeExpression = getExpression(highestLevel);
            testOperator();
            return primeExpression;
        } else if (between('0', '9')) {
            nextToken = Token.CONST;
            checkNextToken();
            primeExpression = getConst(lastUnOperator == Operator.NEG);
        } else if (isVarSym()) {
            nextToken = Token.VAR;
            checkNextToken();
            primeExpression = getVariable();
        } else {
            throw error("Expected primary expression, got '" + curr + "'");
        }
        lastUnOperator = currUnOperator;
        switch (currUnOperator) {
        case LOG:
            return new CheckedLogarithm2(getPrimeExpression());
        case POW:
            return new CheckedPower2(getPrimeExpression());
        case NEG:          
            return Negate.getCompressedNegate(getPrimeExpression());
        case NULL_OP:
            if (negateKostil) {
                primeExpression = new CheckedNegate(primeExpression);
                negateKostil = false;
            }
            return primeExpression;
        default:
            throw error("Unsupported operator" + lastUnOperator);
        }
    }


    private boolean testOperator() throws ParserException {
        if (charToString.containsKey(curr)) {
            getOperator();
            return true;
        }
        throw error("Binary operator or close bracket expected, got '" + curr + "'");
    }

    private void getOperator() throws ParserException {
        String operator = charToString.get(curr);
        expect(operator);
        if (operator.equals("*") && test('*')) {
            operator = "**";
        } else if (operator.equals("/") && test('/')) {
            operator = "//";
        }
        Operator nextOperator = binOperators.get(operator);
        nextToken = nextOperator == Operator.CLOSE_BRACKET
                ? Token.CLOSE_BRACKET
                : Token.BIN_OP;
        lastOperator = nextOperator;
        checkNextToken();
        lastToken = nextToken;

        skipWhiteSpaces();
    }

    private CommonExpression makeExpression(Operator operator, CommonExpression first, CommonExpression second) {
        switch (operator) {
        case ADD:
            return new CheckedAdd(first, second);
        case MUL:
            return new CheckedMultiply(first, second);
        case SUB:
            return new CheckedSubtract(first, second);
        case DIV:
            return new CheckedDivide(first, second);
        case LOGARITHM:
            return new CheckedLogarithm(first, second);
        case POWER:
            return new CheckedPower(first, second);
        // case RIGHT_SHIFT:
        //     return new RightShift(first, second);
        // case LEFT_SHIFT:
        //     return new LeftShift(first, second);
        default:
            throw new IllegalStateException("Unsupported operator" + operator);
        }
    }

    public boolean isVarSym() {
        return between('x', 'z'); // correct?
    }

    private boolean unOpFirstChar() {
        return curr == 'l' || curr == 'p' || curr == '-';
    }

    private void checkNextToken() throws ParserException {
        switch (nextToken) {
        case OPEN_BRACKET:              
        case VAR:
        case CONST:
            if (lastToken == Token.CLOSE_BRACKET || lastToken == Token.VAR || lastToken == Token.CONST) {
                throw error("Unexpected " + nextToken + " after " + lastToken);
            }
            break;
        case BIN_OP:
            if (lastToken != Token.VAR && lastToken != Token.CONST
                    && lastToken != Token.CLOSE_BRACKET) {
                throw error("Unexpected " + nextToken + " after " + lastToken);
            }
            break;
        case UN_OP:
            if (lastToken == Token.CLOSE_BRACKET || lastToken == Token.VAR || lastToken == Token.CONST) {
                throw error("Unexpected " + nextToken + " after " + lastToken);
            }
            break;
        case CLOSE_BRACKET:
            if (lastToken != Token.CLOSE_BRACKET && lastToken != Token.VAR && lastToken != Token.CONST) {
                throw error("Unexpected " + nextToken + " after " + lastToken);
            }
        }
    }


}
