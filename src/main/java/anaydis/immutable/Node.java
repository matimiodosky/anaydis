package anaydis.immutable;

import org.jetbrains.annotations.NotNull;


public class Node<T> implements List<T> {

    public static Node NIL  = new Node<>(null, null);
    private final T elem;
    private final Node<T> tail;


    private Node(T elem, Node<T> tail) {
        this.elem = elem;
        this.tail = tail;
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
        Node<T> reversed = (Node<T>) List.nil(), current = this;
        while (current != null){
            reversed = new Node<>(current.elem, reversed);
            current = current.tail;
        }
        return reversed;
    }
}
