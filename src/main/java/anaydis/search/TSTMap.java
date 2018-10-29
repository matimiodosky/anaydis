package anaydis.search;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TSTMap<V> implements Map<String, V>, Trie<V>{

    @Nullable
    private Node head;
    private int size;
    @Nullable
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

    @Nullable
    @Override
    public V get(@NotNull String key) {
        return get(key, head, 0);
    }

    @Nullable
    private V get(@NotNull String key, @Nullable Node node, int level) {
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

    @Nullable
    @Override
    public V put(@NotNull String key, V value) {
        head = put(key, value, head, 0);
        return lastFound;
    }

    @Nullable
    private Node put(@NotNull String key, V value, @Nullable Node node, int level) {
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

    @NotNull
    @Override
    public Iterator<String> keys() {
        HashSet<String> set = new HashSet<>();
        keys(head, "", set);
        return set.iterator();
    }

    private void keys(@Nullable Node node, String buff, @NotNull HashSet<String> set) {
        if (node == null)return;
        if (node.value !=  null)set.add(buff + node.c);
        keys(node.left, buff, set);
        keys(node.right, buff, set);
        keys(node.middle, buff + node.c, set);
    }

    @NotNull
    public List<String> autocomplete(@NotNull String key){
        List<String> list = new ArrayList<>();
        autocomplete(key, head,"",  0, list);
        return list;
    }

    private void autocomplete(@NotNull String key, @Nullable Node node, String buff, int level, @NotNull List<String> list) {
        if (node != null){
            if (level < key.length()){
                char c = key.charAt(level);
                if (node.c == c) autocomplete(key, node.middle,buff + c,  level + 1, list);
                else if (node.c < c) autocomplete(key, node.right,buff, level, list);
                else autocomplete(key, node.left,buff, level, list);
            }else {
                if (node.middle == null)list.add(buff + node.c);
                autocomplete(key, node.middle, buff + node.c,level + 1,  list);
                autocomplete(key, node.left, buff, level, list);
                autocomplete(key, node.right, buff, level, list);
            }
        }
    }

    private class Node{
        final char c;
        V value;
        @Nullable
        Node left, right, middle;

        Node(char c) {
            this.c = c;
        }
    }
}