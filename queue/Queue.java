package queue;

public interface Queue {
	// Inv: queue != null && size >= 0 && for i in [1..size] a[i] != null
	
	// pre : object != null
	// post: size = size' + 1 && a = {a'[1]..a'[size], object}
    void enqueue(Object object);
	
	// pre : size > 0
	// post : R =  a[1] && size = size' - 1 && a = {a[2]..a[size]}
    Object dequeue();
	
	// pre : size > 0
	// post : size > 0 && R = a[1]
    Object element();
	
	// pre : true
	// post: R = size
    int size();
	
	// pre : true
	// post : R = size == 0
    boolean isEmpty();
	
	// pre : true
	//Post: size == 0
    void clear();
	
	// pre : true
	// post : R = array of {a[1]..a[size]}
	Object[] toArray();
}
