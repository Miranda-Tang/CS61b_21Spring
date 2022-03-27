package deque;

import java.security.PrivateKey;

public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node() {
            item = null;
            prev = next = null;
        }

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    /**
     * Creates an empty linked list deque
     */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel.next = sentinel;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    public void addFirst(T item) {
        Node tmp = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;

        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    public void addLast(T item) {
        Node tmp = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;

        size++;
    }

    /**
     * @return true if deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the number of items in the deque
     */
    public int size() {
        return  size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        size--;
        return tmp.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        return tmp.item;
    }


    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node p, int index) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, --index);
    }
}
