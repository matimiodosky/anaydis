package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class TSTMap <V> implements Map<String, V> {

    private Node head;
    private int size;

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return false;
    }

    @Override
    public V get(@NotNull String key) {
        return null;
    }

    @Override
    public V put(@NotNull String key, V value) {
        head =  put(key, value, head, 0);
        return null;
    }

    private Node put(String key, V value, Node node, int level) {
        if (level < key.length()) {
            if (node == null) {
                final Node newNode = new Node(key.charAt(level));
                newNode.middle = put(key, value, newNode.middle, level + 1);
                if (level == key.length() -1)newNode.value = value;
                return newNode;
            } else {
                final int cmp = Character.compare(node.c, key.charAt(level));
                if (cmp < 0) {
                    node.right = put(key, value, node.right, level);
                    return node;
                } else if (cmp == 0) {
                    node.middle = put(key, value, node.middle, level + 1);
                    return node;
                } else {
                    node.lef = put(key, value, node.middle, level);
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
        return null;
    }

    private class Node {
        V value;
        char c;
        Node lef, middle, right;

        Node(char c) {
            this.c = c;
        }
    }
}
