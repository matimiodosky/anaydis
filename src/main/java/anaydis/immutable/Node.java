package anaydis.immutable;

import org.jetbrains.annotations.NotNull;


public class Node<T> implements List<T> {

    private final T elem;
    private final Node<T> tail;

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
        Node<T> reversed = new Node<>(), current = this;
        while (current != null){
            reversed = new Node<>(current.elem, reversed);
            current = current.tail;
        }
        return reversed;
    }
}
