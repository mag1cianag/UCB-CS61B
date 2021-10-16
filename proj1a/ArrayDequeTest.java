import org.junit.Test;

import static org.junit.Assert.*;
public class ArrayDequeTest {
    @org.junit.Test
    public void addFirst() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addFirst(8);
        array.addFirst(7);
        array.addFirst(6);
        array.addFirst(5);
        array.addFirst(4);
        array.addFirst(3);
        array.addFirst(2);
        array.addFirst(1);
        array.printDeque();
        array.addLast(12);
        array.addLast(11);
        array.addLast(10);
        array.addLast(9);
        array.printDeque();
        System.out.println(array.get(6));
    }

    @org.junit.Test
    public void addLast() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.printDeque();

        array.addLast(9);
        array.addLast(10);
        array.addLast(11);
        array.addLast(12);
        array.printDeque();
    }

    @Test
    public void addMix() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.printDeque();
        array.addFirst(0);
        array.addLast(9);
        array.addLast(10);
        array.addFirst(-1);
        array.printDeque();
    }


    @org.junit.Test
    public void isEmpty() {
    }

    @org.junit.Test
    public void size() {
    }

    @org.junit.Test
    public void printDeque() {
    }

    @org.junit.Test
    public void removeFirst() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.removeFirst();
        array.printDeque();
    }

    @org.junit.Test
    public void removeLast() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.addLast(9);
        array.addLast(10);
        array.addLast(11);
        array.addLast(12);
        array.addLast(13);
        array.addLast(14);
        array.addLast(15);
        array.addLast(16);
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.removeLast();
        array.printDeque();
    }

    @Test
    public void removeMix() {
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addLast(8);
        array.removeLast();
        array.removeFirst();
        array.printDeque();
    }
    @org.junit.Test
    public void get() {
    }
}
