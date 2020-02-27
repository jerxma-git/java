package expression.parser;

public class StringSource implements Source {
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
    public RuntimeException error(String msg) {
        return new RuntimeException(msg);
    }


}