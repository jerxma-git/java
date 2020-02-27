package expression.exceptions;

public interface ExpressionSource {
    public boolean hasNext();
    public ParserException error(String msg);
    public char next();
}