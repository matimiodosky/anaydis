package anaydis.search;

import anaydis.sort.data.StringDataSetGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractMapTest {


    @NotNull
    abstract Map<String, String> getNewInstance();

    @Test
    public void test_size(){
        Map<String, String> map = getNewInstance();
        map.put("11", "1");
        assertEquals(1,map.size());
        map.put("11", "2");
        assertEquals(1, map.size());
        map.put("12", "1");
        assertEquals(2, map.size());
    }

    @Test
    public void test_get_and_put(){
        Map<String, String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        assertEquals("2", map.get("11"));
        assertEquals("1", map.get("21"));
    }

    @Test
    public void test_iterator(){
        Map<String , String> map = getNewInstance();
        map.put("12", "2");
        map.put("13", "3");
        map.put("21", "4");
        map.put("22", "5");
        Iterator<String> iterator = map.keys();
        HashSet<String> keys  = new HashSet<>();
        while (iterator.hasNext()){
            keys.add(iterator.next());
        }
        assertEquals(4, keys.size());
        assertTrue(keys.containsAll(Arrays.asList("12", "13", "21", "22" )));
    }

    @Test
    public void test_get_existing_key(){
        Map<String, String> map = getNewInstance();
        assertNull(map.put("11", "1"));
        assertEquals("1", map.get("11"));
        assertEquals("1", map.put("11", "2"));
        assertEquals("2", map.get("11"));
        map.put("21", "1");
        assertEquals("1", map.get("21"));
    }

    @Test
    public void prefix_keys(){
        Map<String, String> map = getNewInstance();
        map.put("11", "1");
        assertEquals("1", map.get("11"));
        map.put("112", "2");
        assertEquals("2", map.get("112"));
        map.put("21", "1");
        assertEquals("1", map.get("21"));
    }


    @Test
    public void test_contains(){
        Map<String, String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        assertTrue(map.containsKey("11"));
        assertTrue(!map.containsKey("12"));
    }

    @Test
    public void testClear(){
        Map<String, String> map = getNewInstance();
        map.put("11", "1");
        map.put("11", "2");
        map.put("21", "1");
        map.clear();
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());

    }

}
