package anaydis.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class ArrayMapTest {

    @Test
    public void test01_size_0_when_instantiated(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        assertEquals(0, arrayMap.size());
    }

    @Test
    public void test02_size_1_when_item_put(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        assertEquals(1, arrayMap.size());
    }

    @Test
    public void test03_return_null_when_put_first_time(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        assertNull(arrayMap.put(1, "1"));
    }

    @Test
    public void test04_get_returns_item_put(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        assertEquals("1", arrayMap.get(1));
    }

    @Test
    public void test05_return_previous_when_put_again(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        assertEquals("1", arrayMap.put(1, "2"));
    }

    @Test
    public void test06_replace_when_put_again(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        assertEquals("1", arrayMap.put(1, "2"));
        assertEquals("2", arrayMap.get(1));
        assertNull(arrayMap.put(2, "2"));
        assertEquals("2", arrayMap.put(2, "3"));
    }

    @Test
    public void test07_size_0_when_clear(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        arrayMap.clear();
        assertEquals(0, arrayMap.size());
    }

    @Test
    public void test08_iterator_iter_every_key(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        arrayMap.put(2, "2");
        Iterator<Integer> integerIterator = arrayMap.keys();
        List<Integer> keys = new ArrayList<>();
        while (integerIterator.hasNext()){
            keys.add(integerIterator.next());
        }
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
    }

    @Test
    public void test09_put_key_and_contains_it(){
        final ArrayMap<Integer, String> arrayMap = new ArrayMap<>(Integer::compareTo);
        arrayMap.put(1, "1");
        assertTrue(arrayMap.containsKey(1));
    }

}