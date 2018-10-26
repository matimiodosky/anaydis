package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RandomizedTreeMap<K, V> implements Map<K, V> {

    private class Node {
        final K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private class Tuple {
        Node node;
        V value;

        Tuple(Node node, V value) {
            this.node = node;
            this.value = value;
        }
    }

    private Node head;
    private final Comparator<K> comparator;
    private int size;
    private final static double rootInsertionProbability = 0;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.head = null;
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return get(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        return get(head, key);
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        final int cmp = comparator.compare(node.key, key);
        if (cmp == 0) return node.value;
        return cmp > 0 ? get(node.left, key) : get(node.right, key);
    }

    @Override
    public V put(@NotNull K key, V value) {
        Tuple tuple = Math.random() < rootInsertionProbability ? rootPut(head, key, value) : put(head, key, value);
        head = tuple.node;
        return tuple.value;
    }

    private Tuple put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Tuple(new Node(key, value), null);
        }
        int cmp = comparator.compare(key, node.key);
        if (cmp == 0) {
            final V lastFound = node.value;
            node.value = value;
            return new Tuple(node, lastFound);
        } else if (cmp > 0) {
            final Tuple tuple = put(node.right, key, value);
            node.right = tuple.node;
            return new Tuple(node, tuple.value);
        } else {
            final Tuple tuple = put(node.left, key, value);
            node.left = tuple.node;
            return new Tuple(node, tuple.value);
        }
    }

    private Tuple rootPut(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Tuple(new Node(key, value), null);
        }
        int cmp = comparator.compare(key, node.key);
        if (cmp < 0) {
            final Tuple tuple = rootPut(node.left, key, value);
            node.left = tuple.node;
            node = rotateRight(node);
            return new Tuple(node, tuple.value);

        } else if (cmp > 0) {
            final Tuple tuple = rootPut(node.right, key, value);
            node.right = tuple.node;
            node = rotateLeft(node);
            return new Tuple(node, tuple.value);
        } else {
            final V lastFound = node.value;
            node.value = value;
            return new Tuple(node, lastFound);
        }
    }

    private Node rotateRight(Node node) {
        if (node.left == null) return node;
        Node result = node.left;
        node.left = result.right;
        result.right = node;
        return result;
    }

    private Node rotateLeft(Node node) {
        if (node.right == null) return node;
        Node result = node.right;
        node.right = result.left;
        result.left = node;
        return result;
    }

    @Override
    public void clear() {
        this.head = null;
        size = 0;
    }

    @Override
    public Iterator<K> keys() {

        Stack<Node> stack = new Stack<>();

        return new Iterator<K>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return !(current == null && stack.isEmpty());
            }

            @Override
            public K next() {

                if (!hasNext()) throw new NoSuchElementException("No more keys in map");
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                Node exCurrent = stack.pop();
                current = exCurrent.right;
                return exCurrent.key;
            }
        };
    }

}
