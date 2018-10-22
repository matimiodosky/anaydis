package anaydis.compression;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;


public class HuffmanTest {

    @Test
    public void test() throws IOException {
        Huffman h = new Huffman();
        String str = "ABRACADABRA";
        ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        h.encode(input, encoded);
        h.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);
        assertArrayEquals(str.getBytes(), decoded.toByteArray());
    }

}