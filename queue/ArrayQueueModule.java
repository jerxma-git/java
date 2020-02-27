package queue;

import java.util.Arrays;

public class ArrayQueueModule {
    protected static final int DEFAULT_CAPACITY = 1;
    protected static Object[] data = new Object[DEFAULT_CAPACITY];
    protected static int start;
    protected static int end;
    protected static int capacity = DEFAULT_CAPACITY;
    protected static int size = 0;

    public static void enqueue(Object object) {
        ensureCapacity();
        assert size < capacity - 1;
        data[end] = object;
        size++;
        end = (end + 1) % capacity;
        // System.err.println(object + " enqueued, curr size = " + size() + ", start = " +  start + ", end = " + end + ", capacity = " + capacity);
        assert start != end;
    }

    public static Object dequeue() {
        assert size > 0;
        Object ret = data[start];
        start = (start + 1) % capacity;
        size--;
        // System.err.println("dequeue called, curr size = " + size());
        return ret;
    }

    public static void clear() {
        end = start;
        size = 0;
        // System.err.println("clear called, curr size = " + size());
    }

    public static Object element() {
        assert size > 0;
        // System.err.println("element called, return " + data[start]);
        return data[start];
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void ensureCapacity() {
        if (size >= capacity - 1) {
            // System.out.println("enlarg");
            int newCapacity = capacity * 2;
            Object[] newData = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(start + i) % capacity];
            }
            data = newData;
            capacity = newCapacity;
            start = 0;
            end = size;
        }
    }

    public static String toStr() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[(start + i) % capacity])
                .append(", ");
        }
        sb.append(data[(start + size - 1) % capacity]);
        sb.append(']');
        return sb.toString();
    }


}