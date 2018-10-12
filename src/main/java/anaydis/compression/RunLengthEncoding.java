package anaydis.compression;

import com.sun.tools.internal.ws.wsdl.document.Output;
import org.jetbrains.annotations.NotNull;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RunLengthEncoding implements Compressor {

    private static final char SCAPE_CHARACTER = (char) 27;

    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        int read = input.read();
        int counter = 0;
        char counting_char = SCAPE_CHARACTER;

        while (read != -1){

            if ((char)read == counting_char){
                counter++;
            }
            else {
                if (counting_char == SCAPE_CHARACTER){
                    counting_char = (char) read;
                    counter++;
                }else if (counter == 0){
                    output.write(counting_char);
                    counting_char = (char) read;
                }
                else{
                    output.write(SCAPE_CHARACTER);
                    output.write(counter);
                    output.write(counting_char);
                    counter = 1;
                    counting_char = (char) read;
                }
            }
            read = input.read();
        }

        if (counter >0){
            output.write(SCAPE_CHARACTER);
            output.write(counter);
            output.write(counting_char);
        }
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {

    }

    public static void main(String[] args) throws IOException {
        RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
        String aaa = "aaaabbbbccccdddd";


        InputStream original = new ByteArrayInputStream(aaa.getBytes(UTF_8));
        ByteArrayOutputStream encoded = new ByteArrayOutputStream();
        runLengthEncoding.encode(original, encoded);
        OutputStream decoded = new ByteArrayOutputStream();

        runLengthEncoding.decode(new ByteArrayInputStream(encoded.toByteArray()), decoded);
    }
}
