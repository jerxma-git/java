package queue;

import java.util.Arrays;
// def : a[1] .. a[size] - elements of the queue; R - returned val
// inv : size >= 0 && for i in [1..size], a[i] != null 
public class ArrayQueueADT {
    protected static final int DEFAULT_CAPACITY = 1;
    protected int size;
    protected int start;
    protected Object[] data;

    public ArrayQueueADT(int capacity) {
        assert capacity > 0;
        data = new Object[capacity];
        size = start = 0;
    }

    public ArrayQueueADT() {
        this(DEFAULT_CAPACITY);
    }

    // pre : queue != null
    // post : queue.size = queue.size' + 1 && queue.data[(queue.start + queue.size) % queue.data.length] == object
    public static void enqueue(ArrayQueueADT queue, Object object) {
        ensureCapacity(queue);
        queue.data[(queue.start + queue.size) % queue.data.length] = object;
        queue.size++;
    }

    // pre : queue.size > 0 && queue.data != null && queue != null
    // post : size = size' - 1 && data[start'] == null && start == (start + 1) % data.length && data != null
    // R : data[start']
    public static Object dequeue(ArrayQueueADT queue) {
        Object ret = queue.data[queue.start];
        queue.data[queue.start] = null; 
        queue.start = (queue.start + 1) % queue.data.length;
        queue.size--;
        return ret;
    }

    // pre : queue != null && queue.size > 0 && queue.data != null
    // post : queue != null && queue.size > 0 && queue.data != null
    // R : queue.data[start]
    public static Object element(ArrayQueueADT queue) {
        return queue.data[queue.start];
    }

    // pre : queue != null
    // post : queue != null
    // R : queue.size == 0
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // pre : queue != null
    // post : queue.start == queue.size == 0 && queue != null
    public static void clear(ArrayQueueADT queue) {
        queue.start = queue.size = 0;
    }

    // pre : queue != null
    // post : queue != null
    // R : R = queue.size
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // pre : queue != null && queue.data != null && queue.data.length > 0
    // post : queue != null && queue.data.length = queue.data.length' * 2 && for i in [0..size - 1] queue.data[i] = queue.data'[(start + i) % queue.data'.length] && queue.start == 0
    public static void ensureCapacity(ArrayQueueADT queue) {
        if (queue.size >= queue.data.length - 1) {
            Object[] newData = new Object[2 * queue.data.length];
            for (int i = 0; i < queue.size; i++) {
                newData[i] = queue.data[(queue.start + i) % queue.data.length];
            }
            queue.data = newData;
            queue.start = 0;
        }
    }

    // pre : queue != null
    // post : R = "[<a[1]>, <a[2]>, ... <a[size]>]" && size != 0 || R == "[]" && size == 0
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