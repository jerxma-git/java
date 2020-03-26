package queue;


// def a[1]..a[size] - elements of the queue; R - returned value
// inv : size >= 0 && for i in [1..size] a[i] != null

public interface Queue {

    // pre  : inv && element != null
    // post : inv && a' = {a[1]..a[size], element} && size' = size + 1
    void enqueue(Object object);
    
    // pre  : inv && size > 0
    // post : inv && a = {a[1]..a[size - 1]} && size' = size - 1
    Object dequeue();
    
    // pre  : inv
    // post : inv && R = size
    int size();

    // pre  : inv
    // post : inv && R = size == 0
    boolean isEmpty();

    // pre  : inv && size > 0;
    // post : inv && R = a[1];
    Object element();

    // pre  : inv
    // post : inv && a = {}
    void clear();

    // pre  : inv
    // post : inv && R = array && array[i] = a[i + 1] for i in [0..size - 1];
    Object[] toArray();

}