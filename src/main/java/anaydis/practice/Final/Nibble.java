package anaydis.practice.Final;

import anaydis.bit.BitsStringifier;

public class Nibble {

    public static byte nibbleFromString(String string, int n){
        return nNibble((byte) string.charAt(n /2), n % 2 == 0);
    }

    private static byte nNibble(byte b, boolean n) {
        if (n)return (byte) (b & 0b00001111);
        return (byte) ((b & 0b11110000) >> 4);
    }

    public static void main(String[] args) {
        String str = "hola";
        BitsStringifier.printBytes(str);
        BitsStringifier.printBinary(nibbleFromString(str, 7));
    }
}
