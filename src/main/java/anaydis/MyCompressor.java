package anaydis;

import anaydis.compression.Compressor;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MyCompressor implements Compressor {


    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int read = input.read();
        while (read != -1){
            output.write(read);
            System.out.println((char) read );
            read = input.read();
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    public static void main(String[] args) {
        /*MyCompressor myCompressor = new MyCompressor();
        InputStream inputStream = new ByteArrayInputStream("ABRACADABRA".getBytes(UTF_8));
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            myCompressor.encode(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        byte[] arr = "a".getBytes();
        for (byte b : arr) {
            System.out.println(b);
        }
    }
}
