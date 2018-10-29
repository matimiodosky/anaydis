package anaydis.compression;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class MoveToFrontTest {

    @Test
    public void test_dictionary(){

        final Dictionary dict = new Dictionary();
        assertEquals((int)'m', dict.getIndex('m'));
        assertEquals(0, dict.getIndex('m'));
        assertEquals((int)'a' + 1, dict.getIndex('a'));
    }

    @Test
    public void test() throws IOException {
        MoveToFront moveToFront = new MoveToFront();
        InputStream inputStream = new ByteArrayInputStream("mamaamasa".getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        moveToFront.encode(inputStream, encoded);
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        moveToFront.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);
        assertArrayEquals(decoded.toByteArray(), "mamaamasa".getBytes());
    }

}