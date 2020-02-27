package expression.parser;

public class BaseParser {
    protected Source source;
    protected char curr;
    
    public void setSource(Source source) {
        this.source = source;
    }

    public void expect(char ch) {
        if (curr == ch) {
            nextChar();
        } else {
            throw error("Expected: '" + ch + "', found: '" + curr + "'"); 
        }
    }

    public void expect(String str) {
        for (char ch : str.toCharArray()) {
            expect(ch);
        }
    }

    public char nextChar() {
        curr = source.hasNext() ? source.next() : '\0';
        return curr;
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

    public RuntimeException error(String msg) {
        return source.error(msg);
    }

}