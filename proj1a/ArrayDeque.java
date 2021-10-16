import java.util.Arrays;

public class ArrayDeque<T> implements Deque<T> {
    private T[] eles;
    private int size;
    private int capacity;
    private int head;

    public ArrayDeque() {
        capacity = 8;
        eles = (T[]) new Object[capacity];
        head = 0;
    }

    private T[] resize(T[] vals, int assgin) {
        T[] v = (T[]) new Object[assgin];
        int work = this.head;
        for (int i = 0; i < size; i++) {
            v[i] = eles[work];
            work = (work + 1) % capacity;
        }
        head = 0;
        capacity = assgin;
        return v;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            eles = resize(eles, capacity * 2);
        }
        head = (head - 1 + capacity) % capacity;
        eles[head] = item;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            eles = resize(eles, capacity * 2);
        }
        int rear = (head + size) % capacity;
        eles[rear] = item;
        size += 1;
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
        int work = head;
        for (int i = 0; i < size; i++) {
            System.out.print(eles[work] + " ");
            work = (work + 1) % capacity;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T ret = eles[head];
        head = (head + 1) % capacity;
        size -= 1;
        float x = (float) size / capacity;
        if (capacity > 8 && x < 0.25) {
            eles = resize(eles, capacity / 2);
        }
        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int rear = (head + size - 1) % capacity;
        T ret = eles[rear];
        size -= 1;
        float x = (float) size / capacity;
        if (capacity > 8 && x < 0.25) {
            eles = resize(eles, capacity / 2);
        }
        return ret;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        index = (head + index) % capacity;
        return eles[index];
    }
}
