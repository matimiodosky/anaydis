package anaydis.Bits;

import anaydis.bit.Bits;
import anaydis.bit.BitsOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class MyBits extends Bits {
    private int value;
    private byte length;

    public MyBits(String string) {
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                add(bitAt((byte) string.charAt(i), j));
            }
        }
    }

    private boolean bitAt(byte c, int j) {
        return (c >> j & 1) == 1;
    }

    public boolean bitAt(int i){
       return  (value >> i & 1) == 1;
    }

    public MyBits(){}

    @NotNull
    public MyBits add(boolean bit) {
        value = (value << 1) | (bit ? 1 : 0);
        length++;
        return this;
    }

    public int getValue() { return value; }

    public int getLength() { return length; }

    @NotNull
    public MyBits copy() {
        final MyBits bits = new MyBits();
        bits.value = value;
        bits.length = length;
        return bits;
    }

    public void writeInto(@NotNull OutputStream stream) throws IOException {
        stream.write(length);
        final BitsOutputStream output = new BitsOutputStream();
        output.write(this);
        stream.write(output.toByteArray());
    }

    @NotNull
    public static MyBits buildBits(byte length, byte[] code){
        MyBits bits = new MyBits();
        int k = 0;
        for (byte aByte : code) {
            for (int j = Byte.SIZE - 1; j >= 0; j--) {
                if (k == length) return bits;
                bits.add(((aByte >> j) & 1) == 1);
                k++;
            }
        }
        return bits;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyBits myBits = (MyBits) o;
        return value == myBits.value &&
                length == myBits.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, length);
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            byte c = (byte) (value >> (i * Byte.SIZE));
            if (c != 0)stringBuilder.append((char)c);
        }
        return stringBuilder.toString();
    }

    @Override public String toString() {
        final StringBuilder builder = new StringBuilder();
        int aux = 1 << length;
        while(aux > 1) {
            aux = aux >> 1;
            builder.append((value & aux) == 0 ? "0":"1");
        }
        return builder.toString();
    }
}
