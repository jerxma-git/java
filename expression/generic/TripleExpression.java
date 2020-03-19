package expression.generic;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression<T extends Evaluative<T>> extends ToMiniString {
    T evaluate(T x, T y, T z);
}