package queue;

// def : a[1]..a[size] - elements of the queue; R - returned value
public class LinkedQueue extends AbstractQueue {
    private Node head = null;

    protected void enqueueImpl(Object element) {
        if (size == 0) {
            head = new Node(element);
        } else {
            getTail().next = new Node(element);
        }
    }

    private Node getTail() {
        if (size == 0) {
            return null;
        }
        Node tail = head;
        for (int i = 0; i < size - 1; i++) {
            tail = tail.next;
        }
        return tail;
    }

    protected Object dequeueImpl() {
        Object returnedVal = head.value;
        head = head.next;
        return returnedVal;
    }

    protected void clearImpl() {
        head = null;
        System.out.println("cleared");
    }

    public Object elementImpl() {
        return head.value;
    }

    private class Node {
        private Object value;
        private Node next;

        public Node(Object value) {
            assert value != null;
            this.value = value;
        }
    }

    // faster implementation
    // public Object[] toArray() {
    //     Object[] arr = new Object[size];
    //     Node curr = head;
    //     for (int i = 0; i < size; i++) {
    //         arr[i] = curr.value;
    //         curr = curr.next;
    //     }
    //     return arr;
    // }
    
}
