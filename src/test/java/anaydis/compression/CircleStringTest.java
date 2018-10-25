package anaydis.compression;


import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CircleStringTest {

    @Test
    public void test_iterator(){
        CircleString circleString = new CircleString("ABRACADABRA".toCharArray());
        Iterator<Character> iterator = circleString.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        assertEquals("ABRACADABRA", stringBuilder.toString());
    }

    @Test
    public void test_shift(){
        CircleString circleString = new CircleString("ABRACADABRA".toCharArray());
        CircleString shifted = circleString.shift();
        Iterator<Character> iterator = shifted.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        assertEquals("AABRACADABR", stringBuilder.toString());
    }

    @Test
    public void test_double_Shift(){
        CircleString circleString = new CircleString("ABRACADABRA".toCharArray());
        CircleString shifted = circleString.shift().shift();
        Iterator<Character> iterator = shifted.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        assertEquals("RAABRACADAB", stringBuilder.toString());
    }

    @Test
    public void test_comparator(){
        CircleString circleString = new CircleString("ABRACADABRA".toCharArray());
        CircleString shifted = circleString.shift();
        /*
        ABRACADABRA
        AABRACADABR
         */
        assertTrue(circleString.compareTo(shifted) > 0);

        circleString = new CircleString("ABRACADABRA".toCharArray());
        shifted = circleString.shift().shift();
        /*
        ABRACADABRA
        RAABRACADAB
         */
        assertTrue(circleString.compareTo(shifted) < 0);
    }

    @Test
    public void test_to_String(){
        CircleString circleString = new CircleString("ABRACADABRA".toCharArray());
        CircleString shifted = circleString.shift();
        assertEquals(circleString.toString(), "ABRACADABRA");
        assertEquals(shifted.toString(), "AABRACADABR");

    }

}