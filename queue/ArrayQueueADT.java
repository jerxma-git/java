package queue;

import java.util.Arrays;

public class ArrayQueueADT {
    protected static final int DEFAULT_CAPACITY = 1;
    protected int size;
    protected int start;
    protected int end;
    protected Object[] data;
    protected int capacity;

    public ArrayQueueADT(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
        data = new Object[capacity];
        size = 0;
        start = end = 0;
    }

    public ArrayQueueADT() {
        this(DEFAULT_CAPACITY);
    }

    public static void enqueue(ArrayQueueADT queue, Object object) {
        ensureCapacity(queue);
        assert queue.size < queue.capacity;
        queue.data[queue.end] = object;
        queue.end = (queue.end + 1) % queue.capacity;
        queue.size++;
    }

    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        queue.size--;
        Object ret = queue.data[queue.start];
        queue.data[queue.start] = null; 
        queue.start = (queue.start + 1) % queue.capacity;
        return ret;
    }

    public static Object element(ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.data[queue.start];
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static void clear(ArrayQueueADT queue) {
        queue.end = queue.start;
        queue.size = 0;
        //TODO: delete elements
    }

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    public static void ensureCapacity(ArrayQueueADT queue) {
        if (queue.size >= queue.capacity - 1) {
            // System.out.println("enlarg");
            int newCapacity = queue.capacity * 2;
            Object[] newData = new Object[newCapacity];
            for (int i = 0; i < queue.size; i++) {
                newData[i] = queue.data[(queue.start + i) % queue.capacity];
            }
            queue.data = newData;
            queue.capacity = newCapacity;
            queue.start = 0;
            queue.end = queue.size;
        }
    }

    public static String toStr(ArrayQueueADT queue) {
        if (queue.size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < queue.size - 1; i++) {
            sb.append(queue.data[(queue.start + i) % queue.capacity])
                .append(", ");
        }
        sb.append(queue.data[(queue.start + queue.size - 1) % queue.capacity]);
        sb.append(']');
        return sb.toString();
    }

}