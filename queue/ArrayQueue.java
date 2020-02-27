package queue;

import java.util.Arrays;

public class ArrayQueue {
    protected static final int DEFAULT_CAPACITY = 1;
    protected int size;
    protected int start;
    protected int end;
    protected Object[] data;
    protected int capacity;

    public ArrayQueue(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
        data = new Object[capacity];
        size = start = end = 0;
    }

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public void enqueue(Object object) {
        ensureCapacity();
        assert size < capacity - 1;
        data[end] = object;
        end = (end + 1) % capacity;
        size++;
    }

    public Object dequeue() {
        assert size > 0;
        size--;
        Object ret = data[start];
        data[start] = null; 
        start = (start + 1) % capacity;
        return ret;
    }

    public Object element() {
        assert size > 0;
        return data[start];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
        end = start = size = 0;
    }

    public int size() {
        return size;
    }

    public void ensureCapacity() {
        if (size >= capacity - 1) {
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

    public String toStr() {
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