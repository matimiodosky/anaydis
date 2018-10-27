package anaydis.search;


import anaydis.sort.data.StringDataSetGenerator;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.*;


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
        tstMap.put("11", "2");
        tstMap.put("21", "1");
        assertEquals("2", tstMap.get("11"));
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

    @Test
    public void test_get_existing_key(){
        TSTMap<String> tstMap = new TSTMap<>();
        assertNull(tstMap.put("11", "1"));
        assertEquals("1", tstMap.get("11"));
        assertEquals("1", tstMap.put("11", "2"));
        assertEquals("2", tstMap.get("11"));
        tstMap.put("21", "1");
        assertEquals("1", tstMap.get("21"));
    }

    @Test
    public void prefix_keys(){
        TSTMap<String> tstMap = new TSTMap<>();
        tstMap.put("11", "1");
        assertEquals("1", tstMap.get("11"));
        tstMap.put("112", "2");
        assertEquals("2", tstMap.get("112"));
        tstMap.put("21", "1");
        assertEquals("1", tstMap.get("21"));
    }

    @Test
    public void test(){
        TSTMap<Integer> tstMap = new TSTMap<>();
        tstMap.put("hi", 1);
        assertEquals(1, tstMap.get("hi").intValue());
        tstMap.put("no", 2);
        assertEquals(2, tstMap.get("no").intValue());
        tstMap.put("yes", 1);
        assertEquals(1, tstMap.get("yes").intValue());
        tstMap.put("nos", 4);
        assertEquals(4, tstMap.get("nos").intValue());
        System.out.println();
    }

    @Test
    public void test_hdp(){
        TSTMap<Integer> tstMap = new TSTMap<>();
        HashMap<String, Integer> map = new HashMap<>();

        StringDataSetGenerator dataSetGenerator = new StringDataSetGenerator();
        List<String> strings = dataSetGenerator.createRandom(100);

        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            map.put(string, i);
            tstMap.put(string, i);
        }
        System.out.println();
        for (String string : strings) {
            assertEquals(map.get(string), tstMap.get(string));
        }
    }

}