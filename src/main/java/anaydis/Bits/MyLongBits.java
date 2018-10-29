package anaydis.Bits;

import java.util.ArrayList;
import java.util.List;

public class MyLongBits {

    private List<Boolean> list;

    public MyLongBits(String key) {
        list = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < Byte.SIZE; j++) {
                list.add(bitAt(key.charAt(i), j));
            }
        }
    }

    private Boolean bitAt(char c, int i) {
        return ((c >> i) & 1) == 1;
    }

    public MyLongBits() {
        list = new ArrayList<>();
    }

    private MyLongBits(List<Boolean> list) {
        this.list = list;
    }

    public int getLength() {
        return list.size();
    }

    public boolean bitAt(int i) {
        return list.get(i);
    }

    public String getString() {
        StringBuilder stringBuilder = new StringBuilder();
        int byteIndex = 0;
        byte currentByte = 0;

        for (int i = 0; i < list.size(); i++) {
            boolean bit = list.get(i);

            if (bit) {
                currentByte |= 1 << byteIndex;
            }
            byteIndex++;
            if (byteIndex == 8) {
                stringBuilder.append((char) currentByte);
                byteIndex = 0;
                currentByte = 0;
            }

        }
        return stringBuilder.toString();
    }

    public MyLongBits copy() {
        return new MyLongBits(new ArrayList<>(list));
    }

    public MyLongBits add(boolean b) {
        list.add(b);
        return this;
    }
}
