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

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private final Node head;
    private final int size;
    private final Comparator<K> comparator;

    private BinaryTree(Node head, Comparator<K> comparator, int size) {
        this.comparator = comparator;
        this.head = head;
        this.size = size;
    }

    public BinaryTree(Comparator<K> comparator){
        this.comparator = comparator;
        this.head = null;
        this.size = 0;
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
        Result result = put(key, value, head);
        return new BinaryTree<>(result.node, comparator, result.size);
    }

    private Result put(K key, V value, @Nullable Node node) {
        if(node == null)return new Result(new Node(key, value), size+1);
        int cmp = comparator.compare(node.key, key);
        if (cmp>0){
            final Result r = put(key, value, node.left);
            return new Result(new Node(node.key, node.value, r.node, node.right), r.size);
        }
        else if(cmp == 0){
            return new Result(new Node(key, value), size);
        }
        else{
            final Result r = put(key, value, node.right);
            return new Result(new Node(node.key, node.value, node.left, r.node), r.size);
        }
    }

    private class Result{
        Node node;
        int size;
        Result(Node node, int size) {
            this.node = node;
            this.size = size;
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
