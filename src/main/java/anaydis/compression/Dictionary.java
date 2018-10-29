package anaydis.compression;

public class Dictionary{
    private final char[] array ;

    Dictionary() {
        array = new char[Character.MAX_VALUE];
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            array[i] = (char)i;
        }
    }

    int getIndex(char c){
        final int index = indexOf(c);
        moveToFront(index, c);
        return index;
    }

    private int indexOf(char c){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c)return i;
        }
        throw new IllegalArgumentException("Character not in dictionary");
    }

    private void moveToFront(int index, char c){
        System.arraycopy(array, 0, array, 1, index - 1 + 1);
        array[0] = c;
    }

    char getChar(int i) {
        final char c = array[i];
        moveToFront(i, c);
        return c;
    }
}