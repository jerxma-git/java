// package queue;

// public class ArrayQueue {
//     protected static final int DEFAULT_CAPACITY = 1;
//     protected int size;
//     protected int start;
//     protected Object[] data;

//     public ArrayQueue(int capacity) {
//         assert capacity > 0;
//         data = new Object[capacity];
//         size = start = 0;
//     }

//     public ArrayQueue() {
//         this(DEFAULT_CAPACITY);
//     }

//     // pre : true
//     // object != null
//     // post : size = size' + 1 && data[(start + size) % data.length] == object
//     // a' = [a[0]..a[a.size-1], object]
//     public void enqueue(Object object) {
//         ensureCapacity();
//         data[(start + size) % data.length] = object;
//         size++;
//     }

//     // pre : size > 0
//     // post : size = size' - 1 && data[start'] == null && start == (start + 1) % data.length && data != null
//     // post : a' = [a[1]..a[a.size-1]] && R = a[0]
//     // R : data[start']
//     public Object dequeue() {
//         assert size > 0;
//         Object ret = data[start];
//         data[start] = null; 
//         start = (start + 1) % data.length;
//         size--;
//         return ret;
//     }

//     // pre : size > 0 && data != null
//     // post : size > 0 && data != null
//     // R : data[0]
//     public Object element() {
//         return data[start];
//     }

//     // pre : true
//     // post : true
//     // R : size == 0
//     public boolean isEmpty() {
//         return size == 0;
//     }

//     // pre : true
//     // post : size = 0
//     public void clear() {
//         start = size = 0;
//     }

//     // pre : true
//     // post : true
//     // R : size
//     public int size() {
//         return size;
//     }

//     // pre : data != null && data.length > 0
//     // post : data.length = data.length' * 2 && for i in [0..size - 1] data[i] = data'[(start + i) % data'.length] && start == 0
//     private void ensureCapacity() {
//         if (size >= data.length - 1) {
//             Object[] newData = new Object[2 * data.length];
//             for (int i = 0; i < size; i++) {
//                 newData[i] = data[(start + i) % data.length];
//             }
//             data = newData;
//             start = 0;        }
//     }

//     // pre : data != null
//     // post : data != null
//     // R : R = "[a_1, a_2, ... a_n]", where a_i == data[(start + i) % data.length] && n == size) || R == "[]" && size == 0
//     public String toStr() {
//         if (size == 0) {
//             return "[]";
//         }
//         StringBuilder sb = new StringBuilder();
//         sb.append('[');
//         for (int i = 0; i < size - 1; i++) {
//             sb.append(data[(start + i) % data.length])
//                 .append(", ");
//         }
//         sb.append(data[(start + size - 1) % data.length]);
//         sb.append(']');
//         return sb.toString();
//     }

// }