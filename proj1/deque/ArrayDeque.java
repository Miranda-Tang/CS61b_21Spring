package deque;

public class ArrayDeque<T> implements Deque<T> {
    protected T[] items;
    protected int size;
    protected int nextFirst;
    protected int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     *
     * @param index
     * @return the index after rotation (either backwards or forwards)
     */
    public int addOne(int index) {
        return (index + 1) % items.length;
    }

    public int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    /**
     * resize the array so that it is neither beyond capacity nor under the usage rate at 25%
     * @param capacity
     */
    public void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        int index = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            tmp[i] = items[index];
            index = addOne(index);
        }
        nextFirst= capacity - 1;
        nextLast = size;
        items = tmp;
    }

    /**
     * to check whether the array is full so that it needs to be resized
     */
    public void checkFull() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    /**
     * to check whether the array is under the required usage rate so that it needs to be resized
     */
    public void checkWasted() {
        int len = items.length;
        if (len >= 16 && size < len / 4) {
            resize(len / 4);
        }
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
     * @param item
     */
    @Override
    public void addFirst(T item) {
        checkFull();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque. You can assume that item is never null.
     * @param item
     */
    @Override
    public void addLast(T item) {
        checkFull();
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size++;
    }

    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.
     */
    @Override
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
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        checkWasted();
        nextFirst = addOne(nextFirst);
        T tmp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return tmp;
    }

    /**
     * @return the item at the back of the deque. If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        checkWasted();
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
    @Override
    public T get(int index) {
        int gap = addOne(nextFirst);
        while (gap != 0) {
            index = addOne(index);
            gap--;
        }
        return items[index];
    }
}
