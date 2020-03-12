package expression.generic;

// public interface T<T> {
//     T add(T a, T b);
//     T sub(T a, T b);
//     T mul(T a, T b);
//     T div(T a, T b);
//     T parse(String val);
// }

public interface Evaluative<T> {
    T add(T a);
    T sub(T a);
    T mul(T a);
    T div(T a);
    T neg();
    T parse(String val);
}