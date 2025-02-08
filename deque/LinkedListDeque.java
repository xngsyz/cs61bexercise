package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final Node<T> head = new Node<>(null, null, null);
    private int size;

    public LinkedListDeque() {
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public LinkedListDeque(T item) {
        head.next = new Node<>(item, head, head);
        head.prev = head.next;
        size = 1;
    }

    public void addFirst(T item) {
        head.next = new Node<>(item, head, head.next);
        head.next.next.prev = head.next;
        size += 1;
    }

    public void addLast(T item) {
        head.prev = new Node<>(item, head.prev, head);
        head.prev.prev.next = head.prev;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String[] items = new String[size];
        Node<T> p = head.next;
        if (p == head) {
            return;
        }
        for (int i = 0; i < size; i++) {
            items[i] = p.item.toString();
            p = p.next;
        }
        System.out.println(String.join(" ", items));
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = head.prev.item;
        head.prev = head.prev.prev;
        head.prev.next = head;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node<T> currentNode = head.next;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return currentNode.item;
            }
            currentNode = currentNode.next;
        }
        throw new AssertionError();
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(index, head.next);
    }

    private T getRecursiveHelper(int index, Node<T> currentNode) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(index - 1, currentNode.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private static class Node<N> {
        private final N item;
        private Node<N> prev;
        private Node<N> next;

        Node(N i, Node<N> p, Node<N> n) {
            item = i;
            prev = p;
            next = n;
        }

        @Override
        public String toString() {
            if (item == null) {
                return "null";
            }
            return item.toString();
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> p;

        LinkedListDequeIterator() {
            p = head.next;
        }

        public boolean hasNext() {
            return p == head;
        }

        public T next() {
            T item = p.item;
            p = p.next;
            return item;
        }
    }
}