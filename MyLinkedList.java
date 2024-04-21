import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T> {
    private class MyNode {//node class that represents all elements of linked list
        T data;
        MyNode next;
        MyNode previous;
        public MyNode(T data) {//contstructor that initializes node with data
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    private MyNode head;//referencing to first node in the linked list
    private MyNode tail;//referencing to last node in the linked list
    private int size;//initializing original size of linked list

    public MyLinkedList() {//constructor to initialize empty linked list
        head = null;
        tail = null;
        size = 0;
    }
    public boolean isEmpty() {//checking if list is empty
        return size == 0;
    }

    @Override
    public void add(T item) {//adding element to the end of the linked list
        addLast(item);
    }
    @Override
    public void set(int index, T item) {//setting the element at index
        MyNode node = getNode(index);
        node.data = item;
    }
    @Override
    public void add(int index, T item) {//adding element on the index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode prevNode = getNode(index - 1);
            MyNode newNode = new MyNode(item);
            newNode.next = prevNode.next;
            prevNode.next.previous = newNode;
            prevNode.next = newNode;
            newNode.previous = prevNode;
            size++;
        }
    }

    @Override
    public void addFirst(T item) {//adding element at the beginning of the linked list
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {//adding the element on the end of linked list
        MyNode newNode = new MyNode(item);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {//Returning the element at the index
        return getNode(index).data;
    }

    @Override
    public T getFirst() {//returning the first element
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() {//returning the last element
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {//remove element at the index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.previous = null;
        } else if (index == size - 1) {
            tail = tail.previous;
            tail.next = null;
        } else {
            MyNode prevNode = getNode(index - 1);
            prevNode.next = prevNode.next.next;
            prevNode.next.previous = prevNode;
        }
        size--;
    }

    @Override
    public void removeFirst() {//removing first element
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        remove(0);
    }

    @Override
    public void removeLast() {//removing last element
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        remove(size - 1);
    }

    @Override
    public void sort() {//sorting the elements
        throw new UnsupportedOperationException("Sort operation is not supported for doubly linked list");
    }

    @Override
    public int indexOf(Object object) {//returning the index of first mentioning of the some element
        MyNode current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {//returning the index of last mentioning of the some element
        MyNode current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.data.equals(object)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    public boolean contains(Object object) {//checking if the linked list contains some element
        return indexOf(object) != -1;
    }

    @Override
    public T[] toArray() {//returning array with all his elements
        T[] array = (T[]) new Object[size];
        MyNode current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    // clear
    @Override
    public void clear() {//removing all elements
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {//returning number of elements
        return size;
    }

    @Override
    public Iterator<T> iterator() {//returning an iterator over the elements in the linked list
        return new Iterator<T>() {
            private MyNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private MyNode getNode(int index) {//returning node at the index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }


    // Testing
    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        System.out.println("Size: " + myList.size());
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());

        myList.removeFirst();
        System.out.println("After removing first element, first element: " + myList.getFirst());

        myList.addLast(4);
        myList.addFirst(0);

        System.out.println("Index of 3: " + myList.indexOf(3));
        System.out.println("Last index of 4: " + myList.lastIndexOf(4));

        System.out.println("Is 2 in the list? " + myList.contains(2));

        System.out.print("List as array: ");
        Object[] objectArray = myList.toArray();
        Integer[] array = new Integer[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            array[i] = (Integer) objectArray[i];
        }
        for (Integer item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
        myList.clear();
        System.out.println("List cleared, size: " + myList.size());
    }
}