
public class MyQueue<T> {//implementing a queue structure using linked list
    private MyLinkedList<T> list;//initializing the linked list that stores the elements of the queue

    public MyQueue() {//constructor for the queue as the empty linked list
        this.list = new MyLinkedList<>();
    }

    public boolean empty() {//checking if queue is empty
        return list.isEmpty();
    }

    public int size() {//returning number of the elements of the queue
        return list.size();
    }

    public T peek() {//returning the element at the front of the queue without removing it
        if (empty()) {
            throw new IllegalStateException("Queue is empty");//throwing exception if it is empty
        }
        return list.getFirst();
    }

    public void enqueue(T item) {//adding the element to the back of the queue
        list.addLast(item);
    }

    public T dequeue() {//removing and returning the element at the front of the queue
        if (empty()) {
            throw new IllegalStateException("Queue is empty");//throwing exception if its empty
        }
        T firstItem = list.getFirst();
        list.removeFirst();
        return firstItem;
    }
}