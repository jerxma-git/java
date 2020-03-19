package queue;

public interface Queue {
    void enqueue(Object object);
    Object dequeue();
    int size();
    boolean isEmpty();
    Object element();
    void clear();
    Object[] toArray();
    String toStr();
}