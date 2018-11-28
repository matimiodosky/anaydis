package anaydis.sort.practice;
import anaydis.sort.practice.Final.MergeWithFourLists;
import anaydis.sort.practice.Final.Node;
import org.junit.Test;

import java.util.Comparator;

public class MergeWithFourListsTest {


    @Test
    public void testMerge(){
        Node<Integer> node = new Node<>(3, new Node<>(2, new Node<>(6, new Node<>(0, null))));
        MergeWithFourLists.sort(Comparator.naturalOrder(), node);
    }

}