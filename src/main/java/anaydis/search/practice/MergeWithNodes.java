package anaydis.search.practice;

import java.util.Comparator;

public class MergeWithNodes {

    private static class Node<T>{
        T elem;
        Node<T> next;

        Node(T elem) {
            this.elem = elem;
        }
    }

    public static <T> Node<T> sort(Node<T> node, Comparator<T> comparator){
        Node<T> mid = node, j = node;
        while (j!= null && j.next!=null && j.next.next != null){
            mid = mid.next;
            j = j.next;
        }
        Node<T> head2 = mid.next;
        mid.next = null;
        if (node != null && node.next != null) node = sort(node, comparator);
        if (head2 != null && head2.next != null) head2 = sort(head2, comparator);
        return merge(node, head2, comparator);
    }

    private static <T> Node<T> merge(Node<T> head1, Node<T> head2, Comparator<T> comparator) {
        Node<T> i = head1, j = head2, result = new Node<>(null), current = result;
        while (i != null && j != null){
            if (comparator.compare(i.elem, j.elem) < 0){
                current.next = i;
                i = i.next;
            }
            else{
                current.next = j;
                j = j.next;
            }
            current = current.next;
        }
        while (i != null){
            current.next = i;
            i = i.next;
            current = current.next;
        }
        while (j != null){
            current.next = j;
            j = j.next;
            current = current.next;
        }

        return result.next;
    }

    public static void main(String[] args) {
        Node<Integer> list = new Node<>(3);
        list.next = new Node<>(1);
        list.next.next = new Node<>(7);
        list.next.next.next = new Node<>(2);
        list.next.next.next.next = new Node<>(5);
        list.next.next.next.next.next = new Node<>(4);
        list = sort(list, Integer::compareTo);
        System.out.println();
    }

}
