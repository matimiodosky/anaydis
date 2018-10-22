package anaydis.compression;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanTest {

    @Test
    public void test() throws IOException {
        Huffman h = new Huffman();
        String str = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        ByteArrayInputStream input = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        h.encode(input, encoded);
        h.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);
        assertArrayEquals(str.getBytes(), decoded.toByteArray());
    }

}