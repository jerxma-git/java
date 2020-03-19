package queue;


// def : a[1] .. a[size] - elements of the queue; R - returned val
// inv : size >= 0 && for i in [1..size], a[i] != null 
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

    // pre  : inv && object != null
    // post : inv && a' = {a[1]..a[size], object}
    protected void enqueueImpl(Object object) {
        ensureCapacity();
        data[(start + size) % data.length] = object;
    }

    //? a[1] = null ????????????????
    // pre  : inv && size > 0
    // post : R = a[1] && a[1] = null && a' = {a[2]..a[size]}
    protected Object dequeueImpl() {
        Object ret = data[start];
        data[start] = null;
        start = (start + 1) % data.length;
        return ret;
    }

    // pre  : inv
    // post : R = a[1] && inv
    protected Object elementImpl() {
        return data[start];
    }

    //? quaquova huya
    // pre  : inv
    // post : inv && a = {}
    protected void clearImpl() {
        start = 0;
    }

    //? needed?
    // pre : data != null && data.length > 0
    // post : data.length = data.length' * 2 && for i in [0..size - 1] data[i] = data'[(start + i) % data'.length] && start == 0
    private void ensureCapacity() {
        if (size >= data.length - 1) {
            Object[] newData = new Object[2 * data.length];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(start + i) % data.length];
            }
            data = newData;
            start = 0;        }
    }

    // pre  : inv
    // post : inv && R = "[a_1, a_2, ... a_n]", where a_i == data[(start + i) % data.length] && n == size) || R == "[]" && size == 0
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

    // pre  : inv
    // post : inv && arr[i] = a[i - 1] && R = arr 
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data[(start + i) % data.length];
        }
        return arr;
    }
}