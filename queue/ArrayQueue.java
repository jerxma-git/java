package queue;


// def a[1]..a[size] - elements of the queue; R - returned value
// inv : size >= 0 && for i in [1..size] a[i] != null

public class ArrayQueue {
    protected static final int DEFAULT_CAPACITY = 1;
    protected int size;
    protected int start;
    protected Object[] data;

    public ArrayQueue(int capacity) {
        assert capacity > 0;
        data = new Object[capacity];
        size = start = 0;
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    // pre : object != null && inv
    // post : a' = {a[1]..a[size], object}
    public void enqueue(Object object) {
        ensureCapacity();
        assert size < data.length - 1;
        data[end()] = object;
        size++;
    }

    // pre : size > 0 && inv
    // post : a' = {a[2]..a[size]} && R = a[1]
    public Object dequeue() {
        assert size > 0;
        size--;
        Object ret = data[start];
        data[start] = null; 
        start = (start + 1) % data.length;
        return ret;
    }

    // pre : inv
    // post : R = a[1]
    public Object element() {
        assert size > 0;
        return data[start];
    }

    // pre : inv
    // post : R = size == 0
    public boolean isEmpty() {
        return size == 0;
    }

    // pre : inv
    // post : a' = {} && size = 0
    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
        start = size = 0;
    }

    // pre : inv 
    // post : R = size
    public int size() {
        return size;
    }


    private void ensureCapacity() {
        if (size >= data.length - 1) {
            int newCapacity = data.length * 2;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, start, newData, 0, data.length - start);
            System.arraycopy(data, 0, newData, data.length - start, end() + 1);
            data = newData;
            start = 0;
        }
    }

    // pre : inv
    // post : inv && (R = "[a[1], a[2], ... a[size]]" || R == "[]" && size == 0)
    public String toStr() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[(start + i) % data.length])
                .append(", ");
        }
        sb.append(data[(start + size - 1) % data.length]);
        sb.append(']');
        return sb.toString();
    }

    private int end() {
        return (start + size) % data.length;
    }



}