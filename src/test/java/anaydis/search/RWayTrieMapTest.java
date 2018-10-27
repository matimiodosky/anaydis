package anaydis.search;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
        RWayTrieMap<Integer> tstMap = new RWayTrieMap<>();
        tstMap.put("11", 1);
        tstMap.put("12", 2);
        tstMap.put("13", 3);
        tstMap.put("21", 4);
        tstMap.put("22", 5);
        Iterator<String> iterator = tstMap.keys();
        HashSet<String> keys  = new HashSet<>();
        while (iterator.hasNext()){
            keys.add(iterator.next());
        }
        TestCase.assertEquals(5, keys.size());
        TestCase.assertTrue(keys.containsAll(Arrays.asList("11","12", "13", "21", "22" )));
    }

    @Test
    public void test_contains(){
        RWayTrieMap<String> stringRWayTrieMap = new RWayTrieMap<>();
        assertTrue(!stringRWayTrieMap.containsKey("hola"));
        stringRWayTrieMap.put("hola", "hola");
        assertTrue(stringRWayTrieMap.containsKey("hola"));
    }

    @Test
    public void test_auto_complete(){
        RWayTrieMap<Object> tstMap = new RWayTrieMap<>();
        tstMap.put("mariano", "mariano");
        tstMap.put("mateo", "mateo");
        tstMap.put("paula", "paula");
        tstMap.put("patricio", "patricio");
        tstMap.put("pamela", "pamela");
        TestCase.assertTrue(tstMap.autoComplete("ma").containsAll(Arrays.asList("mariano" ,"mateo")));
        TestCase.assertTrue(tstMap.autoComplete("pa").containsAll(Arrays.asList("patricio" ,"paula", "pamela")));

    }
}
