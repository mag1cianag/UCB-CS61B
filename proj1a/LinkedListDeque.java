public class LinkedListDeque<T> implements Deque<T> {
    private final Node<T> head;
    private int size;

    public LinkedListDeque() {
        head = new Node<>();
        head.next = head;
        head.prev = head;
    }

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> last = head.prev;
        Node<T> node = new Node<>(item);
        last.next = node;
        node.prev = last;
        node.next = head;
        head.prev = node;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node<T> work = head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(work.ele + " ");
            work = work.next;
        }
        System.out.println();
    }

    @Override
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

    @Override
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

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> work = head.next;
        for (int i = 0; i <= index; i++) {
            work = work.next;
        }
        return work.ele;
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
