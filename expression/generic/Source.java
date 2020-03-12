package expression.generic;

public interface Source {
    public char next();
    public boolean hasNext();
    public RuntimeException error(String msg);
}