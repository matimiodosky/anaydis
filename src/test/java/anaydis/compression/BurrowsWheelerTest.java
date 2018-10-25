package anaydis.compression;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class BurrowsWheelerTest {

    @Test
    public void test() throws IOException {
        ByteArrayInputStream original = new ByteArrayInputStream("DRDOBBS".getBytes());
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        ByteArrayOutputStream decoded = new ByteArrayOutputStream();

        BurrowsWheeler burrowsWheeler = new BurrowsWheeler();
        burrowsWheeler.encode(original, encoded);
        burrowsWheeler.decode(new ByteArrayInputStream(encoded.toByteArray()),decoded);

        assertArrayEquals("DRDOBBS".getBytes(), decoded.toByteArray());

    }

}