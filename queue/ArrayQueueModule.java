package queue;

import java.util.Arrays;
// def : a[1] .. a[size] - elements of the queue; R - returned val
// inv : size >= 0 && for i in [1..size], a[i] != null 
public class ArrayQueueModule {
    protected static final int DEFAULT_CAPACITY = 1;
    protected static Object[] data = new Object[DEFAULT_CAPACITY];
    protected static int start = 0;
    protected static int size = 0;

    // pre : object != null
    // post :  && size = size' + 1 && a = {a'[1]..a'[size], object}
    public static void enqueue(Object object) {
        assert object != null;
        ensureCapacity();
        data[(start + size) % data.length] = object;
        size++;
    }

    // pre : size > 0
    // post : size = size' - 1 && a = {a[2]..a[size]} && R = a[1]
    //// R : data[start']
    public static Object dequeue() {
        Object ret = data[start];
        start = (start + 1) % data.length;
        size--;
        return ret;
    }

    // pre : true
    // post : size == 0
    public static void clear() {
        start = size = 0;
    }

    // pre : size > 0 
    // post : size > 0 && R = a[1]
    //// R : data[start]
    public static Object element() {
        return data[start];
    }

    // pre : true
    // post : R = size
    //// R : size
    public static int size() {
        return size;
    }

    // pre : true
    // post : R = size == 0
    //// R : size == 0
    public static boolean isEmpty() {
        return size == 0;
    }

    //// pre :  data.length > 0
    //// post : data.length = data.length' * 2 && for i in [0..size - 1] data[i] = data'[(start + i) % data'.length] && start == 0
    private static void ensureCapacity() {
        if (size >= data.length - 1) {
            Object[] newData = new Object[2 * data.length];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(start + i) % data.length];
            }
            data = newData;
            start = 0;
        }
    }

    // pre : true
    // post : R = "[<a[1]>, <a[2]>, ... <a[size]>]" && size != 0 || R == "[]" && size == 0
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