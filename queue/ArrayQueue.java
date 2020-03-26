package queue;


public class ArrayQueue extends AbstractQueue {
    protected static final int DEFAULT_CAPACITY = 1;
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

    protected void enqueueImpl(Object object) {
        ensureCapacity();
        data[end()] = object;
    }

    protected Object dequeueImpl() {
        Object ret = data[start];
        data[start] = null;
        start = (start + 1) % data.length;
        return ret;
    }

    protected Object elementImpl() {
        return data[start];
    }

    protected void clearImpl() {
        start = 0;
    }

    private void ensureCapacity() {
        if (size >= data.length - 1) {
            Object[] newData = new Object[2 * data.length];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(start + i) % data.length];
            }
            data = newData;
            start = 0;        }
    }

    private int end() {
        return (start + size) % data.length;
    }


    // faster implementationS
    // @Override
    // public Object[] toArray() {
    //     Object[] arr = new Object[size];
    //     int rightEnd = Math.min(size, data.length - start);
    //     System.arraycopy(data, start, arr, 0, rightEnd);
    //     System.arraycopy(data, (start + rightEnd) % data.length, arr, rightEnd, size - rightEnd);
    //     return arr;
    // }
}