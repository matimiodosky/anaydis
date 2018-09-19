package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RandomizedTreeMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private Node head;
    private final Comparator<K> comparator;
    private int size;
    private V lastFound;
    private double rootInsertionProbability = 0.6;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.head = null;
        this.comparator = comparator;
    }

    public void setRootInsertionProbability(double rootInsertionProbability) {
        this.rootInsertionProbability = rootInsertionProbability;
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
        head = Math.random() < rootInsertionProbability ? rootPut(head, key, value) : put(head, key, value);
        return lastFound;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            lastFound = null;
            size++;
            return new Node(key, value);
        }
        int cmp = comparator.compare(node.key, key);
        if (cmp == 0) {
            lastFound = node.value;
            node.value = value;
            size++;
            return node;
        }
        if (cmp > 0) {
            node.left = put(node.left, key, value);
            return node;
        } else {
            node.right = put(node.right, key, value);
            return node;
        }
    }

    private Node rootPut(Node node, K key, V value) {

        if (node == null) {
            lastFound = null;
            size++;
            return new Node(key, value);
        }
        int cmp = comparator.compare(node.key, key);
        if (cmp == 0) {
            lastFound = node.value;
            node.value = value;
            return node;
        }
        if (cmp > 0) {
            Node newNode = new Node(key, value);
            newNode.right = node;
            lastFound = null;
            size++;
            //todo rotate
            return newNode;
        } else {
            Node newNode = new Node(key, value);
            newNode.left = node;
            lastFound = null;
            size++;
            //todo rotate
            return newNode;
        }

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
