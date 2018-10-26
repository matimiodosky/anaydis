package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
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
            if (aShifting.getStart() ==  1 ) position =i;
                    output.write(aShifting.last());
        }

        writeInt(position, output);
        System.out.println();
    }

    //encoding utils
    private CircleString readCircleString(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        char[] chars = new char[inputStream.available() + 1];
        int i = 0;
        while (read != -1){
            chars[i++] = (char) read;
            read = inputStream.read();
        }
       return new CircleString(chars);
    }

    @Override
    public void decode(@NotNull InputStream input, @NotNull OutputStream output) throws IOException {
        final byte[] string = readString(input);
        final int[] transformVector = getTVector(string);
        int current = readInt(input);
        for (int i = 0; i < string.length; i++) {
            output.write(string[current]);
            current = transformVector[current];
        }
        System.out.println();
    }

    //decoding utils
    private byte[] readString(InputStream inputStream) throws IOException {
        int read;
        byte[] chars = new byte[inputStream.available() - Integer.BYTES ];
        int i = 0;
        while (i < chars.length ){
            read = inputStream.read();
            chars[i++] = (byte) read;
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

    //bits utils
    private static void writeInt(int value, OutputStream outputStream) throws IOException {
        outputStream.write(value >> 24);
        outputStream.write(value >> 16);
        outputStream.write(value >> 8);
        outputStream.write(value);
        System.out.println();
    }

    private static int readInt(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        inputStream.read(bytes);
        return ByteBuffer.wrap(bytes).getInt();
    }

}
