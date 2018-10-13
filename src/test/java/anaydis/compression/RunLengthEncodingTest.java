package anaydis.compression;

import anaydis.sort.SorterTest;
import org.junit.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class RunLengthEncodingTest{

    @Test
    public void test_single_chars() throws IOException {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        String str = "abc";

        ByteArrayInputStream original = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        runLengthEncoding.encode(original, encoded);
        runLengthEncoding.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);

        assertArrayEquals(str.getBytes(), decoded.toByteArray());
    }

    @Test
    public void test_multiple_chars() throws IOException {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        String str = "aaabbcccccccz";

        ByteArrayInputStream original = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        runLengthEncoding.encode(original, encoded);
        runLengthEncoding.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);

        assertArrayEquals(str.getBytes(), decoded.toByteArray());
    }

    @Test
    public void test_multiple_numbers() throws IOException {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        String str = "122233333";

        ByteArrayInputStream original = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();
        runLengthEncoding.encode(original, encoded);
        runLengthEncoding.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);

        assertArrayEquals(str.getBytes(), decoded.toByteArray());
    }
}