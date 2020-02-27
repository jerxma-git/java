package expression.exceptions;

public class BaseParser {
    protected ExpressionSource source;
    protected char curr;
    
    public void setSource(ExpressionSource source) {
        this.source = source;
    }

    public void expect(char ch) throws ParserException {
        if (curr == ch) {
            nextChar();
        } else {
            throw error("Expected: '" + ch + "', found: '" + curr + "'"); 
        }
    }

    public boolean hasNext() {
        return curr != '\0';
    }

    public void expect(String str) throws ParserException {
        for (char ch : str.toCharArray()) {
            expect(ch);
        }
    }

    public void nextChar() {
        curr = source.hasNext() ? source.next() : '\0';
    }

    public boolean test(char ch) {
        if (ch == curr) {
            curr = source.next();
            return true;
        }
        return false;
    }

    public boolean between(char from, char to) {
        return curr >= from && curr <= to;
    }

    public ParserException error(String msg) {
        return source.error(msg);
    }

    public void skipWhiteSpaces() {
        while (Character.isWhitespace(curr)) {
            nextChar();
        }
    }
}