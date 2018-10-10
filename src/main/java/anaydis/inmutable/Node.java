package anaydis.inmutable;

import anaydis.immutable.List;
import org.jetbrains.annotations.NotNull;
import java.util.LinkedList;


public class Node<T> implements List<T> {

    private T elem;
    private Node<T> tail;

    public Node(T elem, Node<T> tail) {
        this.elem = elem;
        this.tail = tail;
    }

    public Node(T elem) {
        this.elem = elem;
        this.tail = null;
    }

    public Node() {
        this.elem = null;
        this.tail = null;
    }

    @Override
    public T head() {
        return elem;
    }

    @NotNull
    @Override
    public List<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return elem == null;
    }

    @NotNull
    @Override
    public List<T> reverse() {

        LinkedList<T> queue = new LinkedList<T>();
        Node<T> current = this;
        while (current != null){
            queue.push(current.elem);
            current = current.tail;
        }
        Node<T> tail = null, reversed = new Node<>();
        while (!queue.isEmpty()){
            reversed = new Node<>(queue.removeLast(), tail);
            tail = reversed;
        }
        return reversed;
    }
}
