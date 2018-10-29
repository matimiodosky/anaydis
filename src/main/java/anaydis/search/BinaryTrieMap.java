package anaydis.search;

import anaydis.Bits.MyBits;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BinaryTrieMap<V> implements Map<String, V> {

    private class Node{
        V value;
        Node left, right;
    }

    private Node head;
    private int size;
    private V lastFound;

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
        return get(new MyBits(key), head, 0);
    }

    private V get(MyBits bits, Node node, int level) {
        if (node == null || level > bits.getLength())return null;
        if (level == bits.getLength())return node.value;
        Node next = bits.bitAt(level) ? node.right : node.left;
        return get(bits, next, level + 1);
    }

    @Override
    public V put(@NotNull String key, V value) {
        if (key.length() > 4) throw new IllegalArgumentException("Length not supported.");
        head =  put(new MyBits(key), value, head, 0);
        return lastFound;
    }

    private Node put(MyBits bits, V value, Node node, int level) {
        if (node == null){
            node = new Node();
            if (level == bits.getLength()){
                node.value = value;
                size++;
            }
            else {
                if (bits.bitAt(level))node.right = put(bits, value, node.right, level + 1);
                else node.left = put(bits, value, node.left, level + 1);
            }
        }
        else {
            if (level == bits.getLength()){
                lastFound = node.value;
                node.value = value;
            }
            else {
                if (bits.bitAt(level))node.right = put(bits, value, node.right, level + 1);
                else node.left = put(bits, value, node.left, level + 1);
            }
        }
        return node;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<String> keys() {
       List<String> list = new ArrayList<>();
       find(head, new MyBits(), list);
       return list.iterator();
    }

    private void find(Node node, MyBits buff, List<String> list) {
        if (node != null){
            if (node.value != null){
                list.add(buff.getString());
            }
            find(node.right, buff.copy().add(true), list);
            find(node.left, buff.copy().add(false), list);
        }
    }

}
