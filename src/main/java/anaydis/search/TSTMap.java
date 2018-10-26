package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;

public class TSTMap <V> implements Map<String, V> {

    private Node head;
    private int size;
    private V lastFound;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return false;
    }

    @Override
    public V get(@NotNull String key) {
        return get(key, head, 0);
    }

    private V get(String key, Node node, int level) {
        if (node == null || level >= key.length())return null;
        final int cmp = Character.compare(node.c, key.charAt(level));
        if (cmp < 0){
            return get(key, node.right, level);
        }
        else if (cmp == 0){
            if (level == key.length() -1)return node.value;
            return get(key, node.middle, level + 1);
        }
        else {
            return get(key, node.right, level);
        }
    }

    @Override
    public V put(@NotNull String key, V value) {
        head =  put(key, value, head, 0);
        return lastFound;
    }

    private Node put(String key, V value, Node node, int level) {
        if (level < key.length()) {
            if (node == null) {
                final Node newNode = new Node(key.charAt(level));
                newNode.middle = put(key, value, newNode.middle, level + 1);
                if (level == key.length() -1){
                    newNode.value = value;
                    size++;
                }
                return newNode;
            } else {
                final int cmp = Character.compare(node.c, key.charAt(level));
                if (cmp < 0) {
                    node.right = put(key, value, node.right, level);
                    return node;
                } else if (cmp == 0) {
                    if (level == key.length() - 1) {
                        lastFound = node.value;
                        node.value = value;
                    }
                    node.middle = put(key, value, node.middle, level + 1);
                    return node;
                } else {
                    node.left = put(key, value, node.middle, level);
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<String> keys() {
        HashSet<String> keys = new HashSet<>();
        findKeys(head, 0, keys, "");
        return keys.iterator();
    }

    private void findKeys(Node node, int level, HashSet<String> keys, String buff) {
        if (node != null){
            if (node.value != null)keys.add(buff + node.c);
            findKeys(node.middle, level+1, keys, buff + node.c);
            findKeys(node.left, level, keys, buff);
            findKeys(node.right, level, keys, buff);
        }
    }

    private class Node {
        V value;
        char c;
        Node left, middle, right;

        Node(char c) {
            this.c = c;
        }
    }
}
