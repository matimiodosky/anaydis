package anaydis.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public abstract class AbstractMapTest {

    @Test
    public void test01_size_0_when_instantiated(){
        final Map<Integer, String> map = getNewInstance();
        assertEquals(0, map.size());
    }

    @Test
    public void test02_size_1_when_item_put(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        assertEquals(1, map.size());
    }

    @Test
    public void test03_return_null_when_put_first_time(){
        final Map<Integer, String> map = getNewInstance();
        assertNull(map.put(1, "1"));
    }

    @Test
    public void test04_get_returns_item_put(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        assertEquals("1", map.get(1));
    }

    @Test
    public void test05_return_previous_when_put_again(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        assertEquals("1", map.put(1, "2"));
    }

    @Test
    public void test06_replace_when_put_again(){

        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        assertEquals("1", map.put(1, "2"));
        assertEquals("2", map.get(1));
        assertNull(map.put(2, "2"));
        assertEquals("2", map.put(2, "3"));
    }

    @Test
    public void test07_size_0_when_clear(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void test08_iterator_iter_every_key(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        map.put(2, "2");
        Iterator<Integer> integerIterator = map.keys();
        List<Integer> keys = new ArrayList<>();
        while (integerIterator.hasNext()){
            keys.add(integerIterator.next());
        }
        assertTrue(keys.contains(1));
        assertTrue(keys.contains(2));
    }

    @Test
    public void test09_put_key_and_contains_it(){
        final Map<Integer, String> map = getNewInstance();
        map.put(1, "1");
        assertTrue(map.containsKey(1));
    }

    @Test
    public void multiple_put(){
        final Map<Integer, String> map = getNewInstance();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            final int value = random.nextInt(100);
            map.put(value, "" + value);
        }
    }

    @Test
    public void test_is_empty(){
        final Map<Integer, String> map = getNewInstance();
        assertTrue(map.isEmpty());
    }

    @Test
    public void test_get_to_an_existing_key(){
        final Map<Integer, String> map = getNewInstance();
        for (int i = 0; i < 100; i++) {
            map.put(i, "" + i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals("" + i, map.get(i));
        }
    }

    abstract Map<Integer, String> getNewInstance();


}
