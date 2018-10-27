package anaydis.search;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Iterator;

public class TSTMap<V> implements Map<String, V>{

    private Node head;
    private int size;
    private V lastFound;

    public TSTMap() {
        this.head = null;
        this.size = 0;
        lastFound = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return get(key) != null;
    }

    @Override
    public V get(@NotNull String key) {
        return get(key, head, 0);
    }

    private V get(String key, Node node, int level) {
        if (node == null || level >= key.length())return null;
        if (level == key.length() - 1){
            if (node.c == key.charAt(level))return node.value;
            else if (node.c < key.charAt(level))return get(key, node.right, level);
            else return get(key, node.left, level);
        }
        else{
            if (node.c == key.charAt(level))return get(key, node.middle, level+1);
            else if (node.c < key.charAt(level))return get(key, node.right, level);
            else return get(key, node.left, level);
        }
    }

    @Override
    public V put(@NotNull String key, V value) {
        head = put(key, value, head, 0);
        return lastFound;
    }

    private Node put(String key, V value, Node node, int level) {
        if (node == null){
            if (level == key.length() - 1){
                node = new Node(key.charAt(level));
                node.value = value;
                size++;
            }
            else if(level < key.length()){
                node = new Node(key.charAt(level));
                node.middle = put(key, value, node.middle, level + 1);
            }
        }
        else {
            if (level == key.length() -1){
                if (node.c == key.charAt(level)){
                    lastFound = node.value;
                    node.value = value;
                }
                else if(node.c < key.charAt(level)) node.right = put(key, value, node.right, level);
                else node.left = put(key, value, node.left, level);
            }
            else if(level < key.length()) {
                if (node.c == key.charAt(level)) node.middle = put(key, value, node.middle, level + 1);
                else if (node.c < key.charAt(level)) node.right = put(key, value, node.right, level);
                else node.left = put(key, value, node.left, level);
            }
        }
        return node;
    }


    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public Iterator<String> keys() {
        HashSet<String> set = new HashSet<>();
        keys(head, "", set);
        return set.iterator();
    }

    private void keys(Node node, String buff, HashSet<String> set) {
        if (node == null)return;
        if (node.value !=  null)set.add(buff + node.c);
        keys(node.left, buff, set);
        keys(node.right, buff, set);
        keys(node.middle, buff + node.c, set);
    }

    private class Node{
        char c;
        V value;
        Node left, right, middle;

        public Node(char c) {
            this.c = c;
        }
    }
}