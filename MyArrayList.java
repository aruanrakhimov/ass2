import java.util.Iterator;
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;//default initial capacity of array
    private Object[] elements;//array that stores elements of the list
    private int size;//current size of array

    //constructor
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];//Initializing array with default capacity
        this.size = 0;//originally size is 0
    }

    public boolean isEmpty() {// checking if the list is empty
        return size == 0;
    }

    @Override
    public void add(T item) {//adding element to the end of the list
        ensureCapacity();
        elements[size++] = item;
    }

    @Override
    public void set(int index, T item) {//setting the element at index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        elements[index] = item;
    }

    @Override
    public void add(int index, T item) {//adding element on the index
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();//ensuring capacity of the array
        System.arraycopy(elements, index, elements, index + 1, size - index);//shifting element to the right
        elements[index] = item;//inserting new element
        size++;//incrementing size
    }

    @Override
    public void addFirst(T item) {//adding element at the beginning of the array list
        add(0, item);
    }

    @Override
    public void addLast(T item) {//adding the element on the end of array list
        add(size, item);
    }

    @Override
    public T get(int index) {//Returning the element at the index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index];
    }

    @Override
    public T getFirst() {//returning the first element
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[0];
    }

    @Override
    public T getLast() {//returning the last element
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return (T) elements[size - 1];
    }

    @Override
    public void remove(int index) {//remove element at the index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    @Override
    public void removeFirst() {//removing first element
        remove(0);
    }

    @Override
    public void removeLast() {//removing last element
        remove(size - 1);
    }


    @Override
    public void sort() {//sorting the elements
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (((Comparable) elements[j]).compareTo(elements[j + 1]) > 0) {
                    Object temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }


    @Override
    public int indexOf(Object object) {//returning the index of first mentioning of the some element
        for (int i = 0; i < size; i++) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object object) {//returning the index of last mentioning of the some element
        for (int i = size - 1; i >= 0; i--) {
            if (object.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Object object) {//checking if array list contains some element
        return indexOf(object) != -1;
    }


    @Override
    public T[] toArray() {//returning array with all his elements
        return (T[]) java.util.Arrays.copyOf(elements, size);
    }


    @Override
    public void clear() {//removing all elements
        size = 0;
    }


    @Override
    public int size() {//returning number of elements
        return size;
    }


    @Override
    public Iterator<T> iterator() {//returning an iterator over the elements in the array list
        return new Iterator<T>() {
            private int index = 0;//initializing original index for iteration

            @Override
            public boolean hasNext() {
                return index < size;//checking if there is next element in array list
            }

            @Override
            public T next() {
                if (!hasNext()) {//if there is no element
                    throw new java.util.NoSuchElementException();//Throwing an exception
                }
                return (T) elements[index++];//returning the current element and increment the index
            }


        };
    }

    // Ensuring that the array has enough space to add new elements
    private void ensureCapacity() {
        if (size == elements.length) {//if the size equals the array list length
            Object[] newElements = new Object[size * 2];//making new array list with double capacity
            System.arraycopy(elements, 0, newElements, 0, size);//copying elements to the new array list
            elements = newElements;//updating references to the new array
        }
    }


    public static void main(String[] args) {//testing
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);//adding element to the array list
        myList.add(2);
        myList.add(3);

        //information about list
        System.out.println("Size: " + myList.size());
        System.out.println("First element: " + myList.getFirst());
        System.out.println("Last element: " + myList.getLast());


        myList.removeFirst();//removing first element
        System.out.println("After removing first element, first element: " + myList.getFirst());


        myList.addLast(4);//adding element to the end of the array list
        myList.addFirst(0);//adding element to the beginning


        System.out.println("Index of 2: " + myList.indexOf(2));//printing the index of 2 element
        System.out.println("Last index of 3: " + myList.lastIndexOf(3));//printing the last index of 3 element


        myList.sort();//sorting array list and printing it
        System.out.print("Sorted list: ");
        for (Integer item : myList) {
            System.out.print(item + " ");
        }
        System.out.println();


        System.out.println("Is 1 in the list? " + myList.contains(1));//checking if array list contains element 1


        System.out.print("List as array: ");//printing whole array list
        Object[] objectArray = myList.toArray();
        Integer[] array = new Integer[objectArray.length];
        for (int i = 0; i < objectArray.length; i++) {
            array[i] = (Integer) objectArray[i];
        }
        for (Integer item : array) {
            System.out.print(item + " ");
        }
        System.out.println();


        myList.clear();//clearing the array list
        System.out.println("List cleared, size: " + myList.size());
    }
}