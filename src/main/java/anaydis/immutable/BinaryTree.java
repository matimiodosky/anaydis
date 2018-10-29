package anaydis.immutable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class BinaryTree<K, V> implements Map<K, V> {

    private class Node{
        final K key;
        final V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private int size;
    private final Comparator<K> comparator;
    private boolean addedInLastPut;

    private BinaryTree(Node head, Comparator<K> comparator, int size) {
        this.comparator = comparator;
        this.head = head;
        this.size = size;
    }

    public BinaryTree(Comparator<K> comparator){
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return containsKey(key, head);
    }

    private boolean containsKey(K key, @Nullable Node node) {

        if(node == null)return false;
        final int cmp = comparator.compare(key, node.key);
        if (cmp == 0){
            return true;
        }
        return cmp > 0? containsKey(key, node.right):containsKey(key, node.left);

    }

    @Nullable
    @Override
    public V get(@NotNull K key) {
        return get(key, head);

    }

    @Nullable
    private V get(K key, @Nullable Node node) {
        if(node == null)return null;
        final int cmp = comparator.compare(key, node.key);
        if (cmp == 0){
            return node.value;
        }
        return cmp > 0? get(key, node.right):get(key, node.left);
    }

    @NotNull
    @Override
    public Map<K, V> put(@NotNull K key, V value) {
        return new BinaryTree<>(put(key, value, head), comparator, addedInLastPut? size+1 : size);
    }

    private Node put(K key, V value, @Nullable Node node) {

        Node newHead;

        if (node == null){
            addedInLastPut = true;
            return new Node(key, value);
        }
        final int cmp = comparator.compare(key, node.key);

        if (cmp == 0){
            addedInLastPut = false;
            return new Node(key, value);
        }
        if (cmp < 0){
            newHead = new Node(node.key, node.value);
            newHead.left = put(key, value, node.left);
            newHead.right = node.right;
            return newHead;
        }
        else {
            newHead = new Node(node.key, node.value);
            newHead.right = put(key, value, node.right);
            newHead.left = node.left;
            return newHead;
        }
    }


    @Nullable
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
