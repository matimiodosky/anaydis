package anaydis.compression;

import anaydis.bit.Bits;
import anaydis.bit.BitsOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class MyBits extends Bits {
    private int value;
    private byte length;

    public MyBits add(boolean bit) {
        value = (value << 1) | (bit ? 1 : 0);
        length++;
        return this;
    }

    public int getValue() { return value; }

    public int getLength() { return length; }

    public MyBits copy() {
        final MyBits bits = new MyBits();
        bits.value = value;
        bits.length = length;
        return bits;
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

    public void writeInto(OutputStream stream) throws IOException {
        stream.write(length);
        final BitsOutputStream output = new BitsOutputStream();
        output.write(this);
        stream.write(output.toByteArray());
    }

    public static MyBits buildBits(byte length, byte code) {
        return buildBits((byte) 0, length, code);
    }

    public static MyBits buildBits(byte from, byte length, byte code) {
        MyBits bits = new MyBits();
        for (int i = Byte.SIZE - 1 - from; i >= Byte.SIZE - length; i--) {
            bits.add(((code >> i) & 1) == 1);
        }
        return bits;
    }

    @Override
    public boolean equals(Object o) {
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
}