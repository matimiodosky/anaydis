package anaydis.immutable;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class NodeTest{

    @Test
    public void test01_new_list_is_empty(){
        List<Object> node = List.nil();
        assertTrue(node.isEmpty());
    }

    @Test
    public void test02_node_head_returns_element_added(){
        List<String> node = List.cons("a", List.nil());
        assertEquals("a", node.head());
    }

    @Test
    public void test03_node_with_tail_returns_elements_added(){
        List<String> node = List.cons("a", List.cons("b", List.nil()));
        assertEquals("a", node.head());
        assertEquals("b", node.tail().head());
    }

    @Test
    public void test04_reverse_returns_reversed_elements(){
        List<String> node = List.cons("a", List.cons("b", List.cons("c", List.nil())));
        List<String> reversed = node.reverse();
        assertEquals("c", reversed.head());
        assertEquals("b", reversed.tail().head());
        assertEquals("a", reversed.tail().tail().head());
    }

    @Test (expected = IllegalStateException.class)
    public void test_nil_head(){
        Node.NIL.head();
    }

    @Test (expected = IllegalStateException.class)
    public void test_nil_tail(){
        Node.NIL.tail();
    }

    @Test (expected = IllegalStateException.class)
    public void test_nil_reverse(){
        Node.NIL.reverse();
    }

}