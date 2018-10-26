package anaydis.search;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class TSTMapTest {

    @Test
    public void test_size(){
        TSTMap<Integer> tstMap = new TSTMap<>();
        tstMap.put("11", 1);
        assertEquals(1, tstMap.size());
        tstMap.put("11", 2);
        assertEquals(1, tstMap.size());
        tstMap.put("12", 1);
        assertEquals(2, tstMap.size());
    }

    @Test
    public void test_get_and_put(){
        TSTMap<String> tstMap = new TSTMap<>();
        tstMap.put("11", "1");
        assertEquals("1", tstMap.get("11"));
        tstMap.put("11", "2");
        assertEquals("2", tstMap.get("11"));
        tstMap.put("21", "1");
        assertEquals("1", tstMap.get("21"));
    }

    @Test
    public void test_iterator(){
        TSTMap<Integer> tstMap = new TSTMap<>();
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
        assertEquals(5, keys.size());
        assertTrue(keys.containsAll(Arrays.asList("11","12", "13", "21", "22" )));
    }

}