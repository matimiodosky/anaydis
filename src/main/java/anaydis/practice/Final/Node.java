package anaydis.practice.Final;

public class Node<T>{
    T elem;
    Node<T> tail;

    public Node(T elem, Node<T> tail) {
        this.elem = elem;
        this.tail = tail;
    }
}