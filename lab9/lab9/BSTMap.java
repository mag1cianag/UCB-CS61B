package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int c = key.compareTo(p.key);
        if (c == 0) {
            return p.value;
        } else if (c < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }


    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int c = key.compareTo(p.key);
        if (c == 0) {
            p.value = value;
        } else if (c < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> keys = new HashSet<>();
        add(root, keys);
        return keys;
    }


    private void add(Node p, Set<K> keys) {
        if (p == null) {
            return;
        }
        keys.add(p.key);
        add(p.left, keys);
        add(p.right, keys);
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        V v = get(key);
        if (v == null) {
            return null;
        }
        root = remove(key, root);
        this.size -= 1;
        return v;
    }

    /**
     * traverse left
     *
     * @param p subtree
     * @return min node
     */
    private Node min(Node p) {
        if (p.left == null) {
            return p;
        }
        return min(p.left);
    }

    private Node remove(K key, Node p) {
        int c = key.compareTo(p.key);
        if (c > 0) {
            p.right = remove(key, p.right);
        } else if (c < 0) {
            p.left = remove(key, p.left);
        } else {
            if (p.left == null) return p.right;
            if (p.right == null) return p.left;
            Node x = p;
            p = min(x.right);
            p.right = removeMin(x.right);
            p.left = x.left;
        }
        return p;
    }

    private Node removeMin(Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = removeMin(p.left);
        return p;
    }

    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        Map61B<Integer, String> map = new BSTMap<>();
//        for (int i = 0; i < 10; i++) {
//            map.put("hi" + i, String.valueOf(i));
//        }
        map.remove(1);
        System.out.println("x");
    }
}
