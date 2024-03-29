package anaydis.search;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> implements Map<String, V> , Trie<V> {

    private class Node {
        @NotNull
        final Object[] nodes;
        V value;

        Node() {
            this.nodes = new Object[Character.MAX_VALUE];
        }
    }

    @Nullable
    private Node head;
    private int size;
    private V lastFound;

    public RWayTrieMap() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head, key, 0) != null;
    }

    @Nullable
    @Override
    public V get(@NotNull String key) {
        Node result = find(head, key, 0);
        return result != null? result.value : null;
    }

    @Override
    public V put(@NotNull String key, V value) {
        head = put(head, key, value, 0);
        return lastFound;
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private Node put(@Nullable Node node, @NotNull String key, V value, int level) {
        if (node == null){
            node = new Node();
            if (level < key.length()){
                node.nodes[key.charAt(level)] = put((Node) node.nodes[key.charAt(level)], key, value, level + 1);
            }else {
               node.value =value;
               size++;
            }
        }
        else {
            if (level < key.length()){
                node.nodes[key.charAt(level)] = put((Node) node.nodes[key.charAt(level)], key, value, level+1);
            }else {
                lastFound = node.value;
                node.value = value;
            }
        }
        return node;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @NotNull
    @Override
    public Iterator<String> keys() {
        List<String> list = new ArrayList<>();
        keys(head, "", list);
        return list.iterator();
    }

    @SuppressWarnings("unchecked")
    private void keys(@Nullable Node node, String buff, @NotNull List<String> list) {
        if (node != null){
            if (node.value != null)list.add(buff);
            for (int i = 0; i < node.nodes.length; i++) {
                keys((Node) node.nodes[i], buff + (char) i, list);
            }
        }
    }

    @NotNull
    public List<String> autocomplete(@NotNull String key){
        List<String> list = new ArrayList<>();
        autoComplete(key ,head,0,  "", list);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void autoComplete(@NotNull String key, @Nullable Node node, int level, String buff, @NotNull List<String> list) {
        if (node != null){
            if (level < key.length()){
                autoComplete(key, (Node) node.nodes[key.charAt(level)], level +1, buff + key.charAt(level), list);
            }
            else {
                if (node.value != null){
                    list.add(buff);
                }
                else {
                    System.out.println();
                    for (int i = 0; i < node.nodes.length; i++) {
                        autoComplete(key, (Node)node.nodes[i], level + 1, buff + (char) i, list);
                    }
                }
            }
        }
    }

    @Nullable
    @SuppressWarnings("unchecked")
    private Node find(@Nullable Node node, @NotNull String key, int level){
        if (node == null)return null;
        if (level == key.length())return node;
        final int next = (int) key.charAt(level);
        return find((Node) node.nodes[next], key, level+1);
    }
}
