package expression.generic;


/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T extends Evaluative<T>> {
    CommonExpression<T> parse(String expression);
}
