package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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
     * @return the index after rotation (either backwards or forwards)
     */
    protected int addOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    /**
     * resize the array so that it is neither beyond capacity nor under the usage rate at 25%
     */
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        int index = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            tmp[i] = items[index];
            index = addOne(index);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = tmp;
    }

    /**
     * to check whether the array is full so that it needs to be resized
     */
    private void checkFull() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    /**
     * to check whether the array is under the required usage rate so that it needs to be resized
     */
    private void checkWasted() {
        int len = items.length;
        if (len >= 16 && size < len / 4) {
            resize(len / 4);
        }
    }

    /**
     * Adds an item of type T to the front of the deque. You can assume that item is never null.
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

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        ArrayDequeIterator() {
            wizPos = addOne(nextFirst);
        }

        @Override
        public boolean hasNext() {
            return wizPos != nextLast;
        }

        @Override
        public T next() {
            T returnItem = items[wizPos];
            wizPos = addOne(wizPos);
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof  Deque)) {
            return false;
        }
        Deque<T> o = (Deque<T>) obj;
        if (size != o.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(o.get(i))) {
                return false;
            }
        }
        return true;
    }
}
