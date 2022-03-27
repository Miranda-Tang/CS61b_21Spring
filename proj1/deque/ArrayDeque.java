package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    protected int addOne(int index) {
        return (index + 1) % items.length;
    }

    protected int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     * @param item
     */
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     * @param item
     */
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }

    /**
     * @return true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        int index = addOne(nextFirst);
        int cnt = 0;
        while (cnt++ < size) {
            System.out.print(items[index] + " ");
            index = addOne(index);
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = addOne(nextFirst);
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return tmp;
    }

    /**
     * @return the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T tmp = items[nextLast];
        items[nextLast] = null;
        size--;
        return tmp;
    }

    /**
     * @param index
     * @return the item at the given index. If no such item exists, returns null.
     */
    public T get(int index) {
        int gap = addOne(nextFirst);
        while (gap != 0) {
            index = addOne(index);
            gap--;
        }
        return items[index];
    }
}
