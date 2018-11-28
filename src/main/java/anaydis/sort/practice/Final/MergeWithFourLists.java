package anaydis.sort.practice.Final;

import java.util.Comparator;

public class MergeWithFourLists{

    public static <T> Node<T> sort(Comparator<T> comparator, Node<T> list){
        //size 0
        if (list == null) return null;
        //size = 1
        if (list.tail == null){
            return list;
        }
        //size = 2
        if (list.tail.tail == null){
            final Node<T> h2 = list.tail;
            list.tail = null;
            return merge( list, h2, null, null, comparator);
        }
        //size = 3
        if (list.tail.tail.tail == null){
            final Node<T> h2 = list.tail;
            list.tail = null;
            final Node<T> h3 = h2.tail;
            h2.tail = null;
            return merge(list, h2, h3, null, comparator);
        }
        //size = 4
        if (list.tail.tail.tail.tail == null){
            final Node<T> h2 = list.tail;
            list.tail = null;
            final Node<T> h3 = h2.tail;
            h2.tail = null;
            final Node<T> h4 = h3.tail;
            h3.tail = null;
            return merge(list, h2, h3, h4, comparator);
        }
        //size > 4
        Node<T> h2 = list, h3 = list, h4 = list;
        while(list.tail.tail.tail.tail.tail != null){
            h2 = h2.tail;
            h3 = h3.tail.tail;
            h4 = h4.tail.tail.tail;
        }
        Node<T> pointer = h2;
        h2 = pointer.tail;
        pointer.tail = null;

        pointer = h3;
        h3 = pointer.tail;
        pointer.tail = null;

        pointer = h4;
        h4 = pointer.tail;
        pointer.tail = null;

        sort(comparator, list);
        sort(comparator, h2);
        sort(comparator, h3);
        sort(comparator, h4);
        return merge(list, h2, h3, h4, comparator);
    }

    private static <T> Node<T> merge(Node<T> h1, Node<T> h2, Node<T> h3, Node<T> h4, Comparator<T> comparator) {
        return merge(merge(h1, h2, comparator), merge(h3, h4, comparator), comparator);
    }

    private static <T> Node<T> merge(Node<T> h1, Node<T> h2, final Comparator<T> comparator) {
        final Node<T> result = new Node<>(null, null);
        Node<T> current = result;
        while (h1 != null && h2 != null) {
            if (comparator.compare(h1.elem, h2.elem) < 0) {
                current.tail = h1.tail;
                h1 = h1.tail;
            } else {
                current.tail = h2.tail;
                h2 = h2.tail;
            }
            current = current.tail;
        }
        while (h1 != null) {
            current.tail = h1;
            h1 = h1.tail;
            current = current.tail;
        }
        while (h2 != null) {
            current.tail = h2;
            h2 = h2.tail;
            current = current.tail;
        }
        return result.tail;
    }
}
