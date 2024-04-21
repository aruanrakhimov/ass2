
public class MyMinHeap<T extends Comparable<T>> {//implementing min heap structure using array list
    private MyArrayList<T> heap;//array list storing heap elements

    public MyMinHeap() {//contstructor that initializes the heap with an empty array list
        heap = new MyArrayList<>();
    }

    public boolean empty() {//checking if the heap is empty
        return heap.isEmpty();
    }

    public int size() {//returning the number of elements of the heap
        return heap.size();
    }

    public T getMin() {//returns minimum element in the heap and doesn't remove it
        if (empty()) {//checking if its empty
            throw new IllegalStateException("Heap is empty");//throws an exceptions if its empty
        }
        return heap.get(0);
    }

    public T extractMin() {//removing and returning the minimum element in the heap
        if (empty()) {//checking if its empty
            throw new IllegalStateException("Heap is empty");//throws an exception if its true
        }
        T min = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapifyDown(0);
        return min;
    }

    public void insert(T item) {//adding the new element in the heap
        heap.add(item);
        traverseUp(heap.size() - 1);
    }

    private void heapifyDown(int index) {//restoring the heap property by moving an element down the tree
        int leftChild = leftChildOf(index);
        int rightChild = rightChildOf(index);
        int smallest = index;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(smallest)) < 0) {
            smallest = leftChild;
        }
        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(smallest)) < 0) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void traverseUp(int index) {//restoring the heap property by moving element up the tree
        while (index > 0 && heap.get(index).compareTo(heap.get(parentOf(index))) < 0) {
            swap(index, parentOf(index));
            index = parentOf(index);
        }
    }

    private int leftChildOf(int index) {//calculating the index of the left child at the index
        return 2 * index + 1;
    }

    private int rightChildOf(int index) {//calculating the index of the right child at the index
        return 2 * index + 2;
    }

    private int parentOf(int index) {//calculating the index of the parent at the index
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {//swaping the elements at the index
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
