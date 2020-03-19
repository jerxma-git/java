package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    protected static final int DEFAULT_CAPACITY = 1;
    protected static Object[] data = new Object[DEFAULT_CAPACITY];
    protected static int start;
    protected static int size = 0;

    // pre : object != null && inv
    // post : a' = {a[1]..a[size], object}
    public static void enqueue(Object object) {
        ensureCapacity();
        assert size < data.length - 1;
        data[end()] = object;
        size++;
    }

    // pre : size > 0 && inv
    // post : a' = {a[2]..a[size]} && R = a[1]
    public static Object dequeue() {
        assert size > 0;
        Object ret = data[start];
        start = (start + 1) % data.length;
        size--;
        return ret;
    }

    // pre : inv
    // post : a' = {} && size = 0
    public static void clear() {
        start = size = 0;
    }

    // pre : inv
    // post : R = a[1]
    public static Object element() {
        assert size > 0;
        return data[start];
    }

    // pre : inv 
    // post : R = size
    public static int size() {
        return size;
    }

    // pre : inv
    // post : R = size == 0
    public static boolean isEmpty() {
        return size == 0;
    }

    

    private static void ensureCapacity() {
        if (size >= data.length - 1) {
            int newCapacity = data.length * 2;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(data, start, newData, 0, data.length - start);
            System.arraycopy(data, 0, newData, data.length - start, end() + 1);
            data = newData;
            start = 0;
        }
    }

    private static int end() {
        return (start + size) % data.length;
    }

    // pre : inv
    // post : inv && (R = "[a[1], a[2], ... a[size]]" || R == "[]" && size == 0)
    public static String toStr() {
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


}