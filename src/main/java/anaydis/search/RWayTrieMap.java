package anaydis.search;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> implements Map<String, V> {

    private class Node {
        final Object[] nodes;
        V value;

        Node( V value) {
            this.nodes = new Object[256];
            this.value = value;
        }

        public Node() {
            this.nodes = new Object[256];
        }
    }

    private Node head;
    private int size;
    private final List<String> keys;
    private V lastFound;

    public RWayTrieMap() {
        this.head = null;
        this.size = 0;
        this.keys = new ArrayList<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head, key, 0) != null;
    }

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

    @SuppressWarnings("unchecked")
    private Node put(Node node, String key, V value, int level) {
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
        keys.clear();
    }

    @Override
    public Iterator<String> keys() {
        List<String> list = new ArrayList<>();
        keys(head, "", list);
        return list.iterator();
    }

    @SuppressWarnings("unchecked")
    private void keys(Node node, String buff, List<String> list) {
        if (node != null){
            if (node.value != null)list.add(buff);
            for (int i = 0; i < node.nodes.length; i++) {
                keys((Node) node.nodes[i], buff + (char) i, list);
            }
        }
    }

    public List<String> autoComplete(String key){
        List<String> list = new ArrayList<>();
        autoComplete(key ,head,0,  "", list);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void autoComplete(String key, Node node,int level,  String buff, List<String> list) {
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

    @SuppressWarnings("unchecked")
    private Node find(Node node, String key, int level){
        if (node == null)return null;
        if (level == key.length())return node;
        final int next = (int) key.charAt(level);
        return find((Node) node.nodes[next], key, level+1);
    }
}
