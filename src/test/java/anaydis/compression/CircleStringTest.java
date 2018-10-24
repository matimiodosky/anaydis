package anaydis.compression;


import org.junit.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class CircleStringTest {

    @Test
    public void test_iterator(){
        CircleString circleString = new CircleString("ABRACADABRA");
        Iterator<Character> iterator = circleString.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        assertEquals("ABRACADABRA", stringBuilder.toString());
    }

    @Test
    public void test_shift(){
        CircleString circleString = new CircleString("ABRACADABRA");
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
        CircleString circleString = new CircleString("ABRACADABRA");
        CircleString shifted = circleString.shift().shift();
        Iterator<Character> iterator = shifted.iterator();
        StringBuilder stringBuilder = new StringBuilder();
        while (iterator.hasNext()){
            stringBuilder.append(iterator.next());
        }
        assertEquals("RAABRACADAB", stringBuilder.toString());
    }

}