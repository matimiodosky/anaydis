package anaydis.inmutable;

import anaydis.immutable.Map;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class BinaryTreeTest {

    @Test
    public void test01_test_put(){
        Map<Integer, String> binaryTree = new BinaryTree<>(Integer::compareTo);
        Map<Integer, String> second = binaryTree.put(1, "1");
        Map<Integer, String> third = second.put(2, "2");
        Map<Integer, String> fourth = third.put(0, "0");
        Map<Integer, String> fifth = fourth.put(3, "3");

        assertTrue(second.containsKey(1));

        assertTrue(third.containsKey(1));
        assertTrue(third.containsKey(2));

        assertTrue(fourth.containsKey(0));
        assertTrue(fourth.containsKey(2));
        assertTrue(fourth.containsKey(0));

        assertTrue(fifth.containsKey(0));
        assertTrue(fifth.containsKey(1));
        assertTrue(fifth.containsKey(2));
        assertTrue(fifth.containsKey(3));


        assertFalse(binaryTree.containsKey(0));
        assertFalse(binaryTree.containsKey(1));
        assertFalse(binaryTree.containsKey(2));
        assertFalse(binaryTree.containsKey(3));

        assertFalse(second.containsKey(0));
        assertFalse(second.containsKey(2));
        assertFalse(second.containsKey(3));

        assertFalse(third.containsKey(0));
        assertFalse(third.containsKey(3));

        assertFalse(fourth.containsKey(3));

    }

    @Test
    public void test02_test_get(){
        Map<Integer, String> binaryTree = new BinaryTree<Integer, String>(Integer::compareTo).put(1, "1").put(2, "2").put(0, "0").put(3, "3");

        assertEquals("0", binaryTree.get(0));
        assertEquals("1", binaryTree.get(1));
        assertEquals("2", binaryTree.get(2));
        assertEquals("3", binaryTree.get(3));
    }

    @Test
    public void test_size_is_empty(){
        Map<Integer, String> binaryTree = new BinaryTree<>(Integer::compareTo);
        Map<Integer, String> second = binaryTree.put(1, "1");
        Map<Integer, String> third = second.put(2, "2");
        Map<Integer, String> fourth = third.put(0, "0");
        Map<Integer, String> fifth = fourth.put(3, "3");
        Map<Integer, String> sixth = fifth.put(3, "3");


        assertTrue(binaryTree.isEmpty());
        assertEquals(1, second.size());
        assertEquals(2, third.size());
        assertEquals(3, fourth.size());
        assertEquals(4, fifth.size());
        assertEquals(4, sixth.size());

    }

    @Test
    public void test_iterator(){
        Map<Integer, String> binaryTree = new BinaryTree<Integer, String>(Integer::compareTo).put(1, "1").put(2, "2").put(0, "0").put(3, "3");
        Iterator<Integer> integerIterator = binaryTree.keys();
        ArrayList<Integer> keys = new ArrayList<>();
        while (integerIterator.hasNext()){
            keys.add(integerIterator.next());
        }
        assertTrue(keys.contains(0));
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
        assertTrue(keys.contains(3));
    }
}