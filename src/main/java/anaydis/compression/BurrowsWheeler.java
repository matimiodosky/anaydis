package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class BurrowsWheeler implements Compressor {
    @Override
    public void encode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        CircleString original = readCircleString(input);
        CircleString[] shifting = new CircleString[original.length()];
        shifting[0] = original;
        for (int i = 1; i < shifting.length; i++) {
            shifting[i] = shifting[i - 1].shift();
        }

        Arrays.sort(shifting);
        int position = -1;

        for (int i = 0; i < shifting.length; i++) {
            CircleString aShifting = shifting[i];
            if (aShifting.getStart() ==  1) position =i;
                    output.write(aShifting.last());
        }

        output.write(position);
    }

    //encoding utils
    private CircleString readCircleString(InputStream inputStream) throws IOException {
       return new CircleString(readString(inputStream));
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        final byte[] inputArray = streamToArray(input);
        final byte[] string = Arrays.copyOfRange(inputArray, 0, inputArray.length - 1);
        final int[] transformVector = getTVector(string);
        int current = (int) inputArray[inputArray.length - 1];
        for (int i = 0; i < string.length; i++) {
            output.write(string[current]);
            current = transformVector[current];
        }
    }

    private byte[] streamToArray(InputStream input) throws IOException {
        final byte[] array = new byte[input.available()];
        byte read = (byte) input.read();
        int i = 0;
        while (read != -1){
            array[i++] = read;
            read = (byte) input.read();
        }
        return array;
    }

    //decoding utils
    private char[] readString(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        char[] chars = new char[inputStream.available() + 1];
        int i = 0;
        while (read != -1){
            chars[i++] = (char) read;
            read = inputStream.read();
        }
        return chars;
    }

    private int[] getTVector(byte[] string){
        byte[] sorted = Arrays.copyOf(string, string.length);
        byte[] stringCopy = Arrays.copyOf(string, string.length);
        Arrays.sort(sorted);
        int[] t = new int[stringCopy.length];

        for (int i = 0; i < string.length; i++) {
            t[i] = remove(sorted[i], stringCopy);
        }
        return t;
    }

    private int remove(byte c, byte[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c){
                chars[i] = -1;
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
