package anaydis.search;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class RWayTrieMapTest  {

    @Test
    public void test_put_returns_null(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        assertNull(stringRWayTrieMap.put("hola", "hola"));
    }

    @Test
    public void test_put_returns_previous(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        stringRWayTrieMap.put("hola", "hola");
        assertEquals("hola", stringRWayTrieMap.put("hola", "chau"));
    }

    @Test
    public void test_get(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        stringRWayTrieMap.put("hola", "hola");
        assertEquals("hola", stringRWayTrieMap.get("hola"));
    }

    @Test
    public void test_get_returns_null(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        assertNull(stringRWayTrieMap.get("hola"));
    }

    @Test
    public void test_clear(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        stringRWayTrieMap.put("hola", "hola");
        assertEquals(1, stringRWayTrieMap.size());
        stringRWayTrieMap.clear();
        assertEquals(0, stringRWayTrieMap.size());
    }

    @Test
    public void test_iterator(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        stringRWayTrieMap.put("hola", "hola");
        Iterator<String> iterator = stringRWayTrieMap.keys();
        assertTrue(iterator.hasNext());
        assertEquals("hola", iterator.next());
        assertTrue(!iterator.hasNext());
    }

    @Test
    public void test_contains(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        assertTrue(!stringRWayTrieMap.containsKey("hola"));
        stringRWayTrieMap.put("hola", "hola");
        assertTrue(stringRWayTrieMap.containsKey("hola"));
    }
}
