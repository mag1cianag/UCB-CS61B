public class LinkedListDeque<T> {
    private final Node<T> head;
    private int size;

    public LinkedListDeque() {
        head = new Node<>();
        head.next = head;
        head.prev = head;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    public void addLast(T item) {
        Node<T> last = head.prev;
        Node<T> node = new Node<>(item);
        last.next = node;
        node.prev = last;
        node.next = head;
        head.prev = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> work = head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(work.ele + " ");
            work = work.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> d = head.next;
        head.next = d.next;
        d.next.prev = head;
        size--;
        return d.ele;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> last = head.prev;
        head.prev = last.prev;
        last.prev.next = head;
        size--;
        return last.ele;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> work = head.next;
        for (int i = 0; i < index; i++) {
            work = work.next;
        }
        return work.ele;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getHelp(head.next, index).ele;
    }

    private Node<T> getHelp(Node<T> h, int index) {
        if (index == 0) {
            return h;
        }
        return getHelp(h.next, index - 1);
    }

    private static class Node<T> {
        private final T ele;
        private Node<T> prev;
        private Node<T> next;

        Node() {
            ele = null;
            prev = null;
            next = null;
        }

        Node(T ele) {
            this.ele = ele;
        }
    }
}
