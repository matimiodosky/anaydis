package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class MoveToFront implements Compressor{

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Dictionary dictionary = new Dictionary();
        byte read = (byte) input.read();
        while (read != -1){
            output.write(dictionary.getIndex((char) read));
            read = (byte) input.read();
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        Dictionary dictionary = new Dictionary();
        byte read = (byte) input.read();
        while (read != -1){
            output.write(dictionary.getChar(read));
            read = (byte)input.read();
        }
    }


}
