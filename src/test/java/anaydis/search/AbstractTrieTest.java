package anaydis.search;

import anaydis.sort.data.StringDataSetGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractTrieTest {

     @NotNull
     abstract Trie<String> getNewInstance();

    @Test
    public void test_auto_complete(){
        Trie<String> tstMap = getNewInstance();
        tstMap.put("mariano", "");
        tstMap.put("mateo", "");
        tstMap.put("paula", "");
        tstMap.put("patricio", "");
        tstMap.put("pamela", "");
        List<String> autocomplete = tstMap.autocomplete("ma");
        assertTrue(autocomplete.containsAll(Arrays.asList("mariano" ,"mateo")));
    }

    @Test
    public void test_size(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        assertEquals(1,map.size());
        map.put("11", "2");
        assertEquals(1, map.size());
        map.put("12", "1");
        assertEquals(2, map.size());
    }

    @Test
    public void test_get_and_put(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        assertEquals("2", map.get("11"));
        assertEquals("1", map.get("21"));
    }

    @Test
    public void test_iterator(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        map.put("12", "2");
        map.put("13", "3");
        map.put("21", "4");
        map.put("22", "5");
        Iterator<String> iterator = map.keys();
        HashSet<String> keys  = new HashSet<>();
        while (iterator.hasNext()){
            keys.add(iterator.next());
        }
        assertEquals(5, keys.size());
        assertTrue(keys.containsAll(Arrays.asList("11","12", "13", "21", "22" )));
    }

    @Test
    public void test_get_existing_key(){
        Trie<String> map = getNewInstance();
        assertNull(map.put("11", "1"));
        assertEquals("1", map.get("11"));
        assertEquals("1", map.put("11", "2"));
        assertEquals("2", map.get("11"));
        map.put("21", "1");
        assertEquals("1", map.get("21"));
    }

    @Test
    public void prefix_keys(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        assertEquals("1", map.get("11"));
        map.put("112", "2");
        assertEquals("2", map.get("112"));
        map.put("21", "1");
        assertEquals("1", map.get("21"));
    }

    @Test
    public void test_contains(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        assertTrue(map.containsKey("11"));
        assertTrue(!map.containsKey("12"));
    }

    @Test
    public void test_clear(){
        Trie<String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        map.clear();
        assertEquals(0, map.size());
    }
}
