package expression.generic;

public interface Type<T> {
    T parse(String str);
}