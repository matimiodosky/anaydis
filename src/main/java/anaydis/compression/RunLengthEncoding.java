package anaydis.compression;
import org.jetbrains.annotations.NotNull;

import java.io.*;


public class RunLengthEncoding implements Compressor {

    private static final char SCAPE_CHARACTER = (char) 27;

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int read = input.read();
        int counter = 0;
        char counting_char = SCAPE_CHARACTER;

        while (read != -1){


            if (counting_char == SCAPE_CHARACTER){
                    counting_char = (char) read;
                    counter++;
            }
            else if ((char)read == counting_char){
                counter++;
            }
            else {
                output.write(counter);
                output.write(counting_char);
                counter = 1;
                counting_char = (char) read;
            }
            read = input.read();
        }

        if (counter >0){
            output.write(counter);
            output.write(counting_char);
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

        while (input.available() > 0){
            final int count = input.read();
            final char c = (char) input.read();
            for (int i = 0; i < count; i++) {
                output.write(c);
            }
        }
    }

}
