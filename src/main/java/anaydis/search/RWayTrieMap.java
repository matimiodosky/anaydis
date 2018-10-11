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
        V lastFound = this.lastFound;
        this.lastFound = null;
        keys.add(key);
        return lastFound;
    }

    @SuppressWarnings("unchecked")
    private Node put(Node node, String key, V value, int level) {
        if (node == null) {
            Node result = new Node(value);
            if (level < key.length()) {
                final int next = key.charAt(level);
                result.nodes[next]= put((Node) result.nodes[next], key, value, level + 1);
            }else {
                size ++;
            }
            return result;
        }
        if (level == key.length()){
            lastFound = node.value;
            node.value = value;
            return node;
        }
        else {
            final int next = key.charAt(level);
            node.nodes[next] =  put((Node) node.nodes[next], key, value, level+1);
            return node;
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        keys.clear();
    }

    @Override
    public Iterator<String> keys() {
        return keys.iterator();
    }

    @SuppressWarnings("unchecked")
    private Node find(Node node, String key, int level){
        if (node == null)return null;
        if (level == key.length())return node;
        final int next = (int) key.charAt(level);
        return find((Node) node.nodes[next], key, level+1);
    }
}
