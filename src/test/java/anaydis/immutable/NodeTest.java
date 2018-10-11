package anaydis.immutable;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class NodeTest{

    @Test
    public void test01_new_list_is_empty(){
        Node<String> node = new Node<>();
        assertTrue(node.isEmpty());
    }

    @Test
    public void test02_node_head_returns_element_added(){
        Node<String> node = new Node<>("a");
        assertEquals("a", node.head());
    }

    @Test
    public void test03_node_with_tail_returns_elements_added(){
        Node<String> node = new Node<>("a", new Node<>("b"));
        assertEquals("a", node.head());
        assertEquals("b", node.tail().head());
    }

    @Test
    public void test04_reverse_returns_reversed_elements(){
        Node<String> node = new Node<>("a", new Node<>("b", new Node<>("c")));
        List<String> reversed = node.reverse();
        assertEquals("c", reversed.head());
        assertEquals("b", reversed.tail().head());
        assertEquals("a", reversed.tail().tail().head());
    }

}