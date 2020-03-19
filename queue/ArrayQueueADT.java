package queue;

import java.util.Arrays;

// def a[1]..a[size] - elements of the given queue (a[i] = queue.data[i]); R - returned value
// inv : queue.size >= 0 && for i in [1..size] a[i] != null

public class ArrayQueueADT {
    protected static final int DEFAULT_CAPACITY = 1;
    protected int size;
    protected int start;
    protected Object[] data;

    public ArrayQueueADT(int capacity) {
        assert capacity > 0;
        data = new Object[data.length];
        start = size = 0;
    }

    public ArrayQueueADT() {
        this(DEFAULT_CAPACITY);
    }

    // pre : object != null && inv && queue != null
    // post : a' = {a[1]..a[size], object}
    public static void enqueue(ArrayQueueADT queue, Object object) {
        ensureCapacity(queue);
        assert queue.size < queue.data.length;
        queue.data[end(queue)] = object;
        queue.size++;
    }

    // pre : size > 0 && inv && queue != null
    // post : a' = {a[2]..a[size]} && R = a[1]
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.size--;
        Object ret = queue.data[queue.start];
        queue.data[queue.start] = null; 
        queue.start = (queue.start + 1) % queue.data.length;
        return ret;
    }

    // pre : inv && queue != null
    // post : R = a[1]
    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.data[queue.start];
    }

    // pre : inv && queue != null
    // post : R = size == 0
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // pre : inv && queue != null
    // post : a' = {} && size = 0
    public static void clear(ArrayQueueADT queue) {
        queue.start = queue.size = 0;
    }

    // pre : inv && queue != null
    // post : R = size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    private static void ensureCapacity(ArrayQueueADT queue) {
        if (queue.size >= queue.data.length - 1) {
            int newCapacity = queue.data.length * 2;
            Object[] newData = new Object[newCapacity];
            System.arraycopy(queue.data, queue.start, newData, 0, queue.data.length - queue.start);
            System.arraycopy(queue.data, 0, newData, queue.data.length - queue.start, end(queue) + 1);
            queue.data = newData;
            queue.start = 0;
        }
    }

    private static int end(ArrayQueueADT queue) {
        return (queue.start + queue. size) % queue.data.length;
    }

    // pre : inv && queue != null
    // post : inv && (R = "[a[1], a[2], ... a[size]]" || R == "[]" && size == 0)
    public static String toStr(ArrayQueueADT queue) {
        if (queue.size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < queue.size - 1; i++) {
            sb.append(queue.data[(queue.start + i) % queue.data.length])
                .append(", ");
        }
        sb.append(queue.data[(queue.start + queue.size - 1) % queue.data.length]);
        sb.append(']');
        return sb.toString();
    }

}