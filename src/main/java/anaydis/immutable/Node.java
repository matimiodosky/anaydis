package anaydis.immutable;

import org.jetbrains.annotations.NotNull;


public class Node<T> implements List<T> {

    static final List NIL  = new List() {
        @NotNull
        @Override
        public Object head() {
            throw new IllegalStateException();
        }

        @NotNull
        @Override
        public List tail() {
            throw new IllegalStateException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @NotNull
        @Override
        public List reverse() {
            throw new IllegalStateException();
        }
    };

    private final T elem;
    private final List<T> tail;


    public Node(T elem, List<T> tail) {
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
        List<T> reversed = List.nil(), current = this;
        while (!current.isEmpty()){
            reversed = List.cons(current.head(), reversed);
            current = current.tail();
        }
        return reversed;
    }
}
