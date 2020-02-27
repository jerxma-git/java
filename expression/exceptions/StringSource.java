package expression.exceptions;



public class StringSource implements ExpressionSource {
    private String source;
    private int pos;
    public StringSource(String source) {
        this.source = source;
        pos = 0;
    }

    @Override
    public char next() {
        return source.charAt(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < source.length();
    }

    @Override
    public ParserException error(String msg) {
        // return new ParserException(pos + ": " + msg);
        return new ParserException(new StringBuilder().append(pos).append(": ").append(msg).toString());
    }


}