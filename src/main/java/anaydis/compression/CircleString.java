package anaydis.compression;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;

public class CircleString implements Comparable<CircleString>{

    private char[] string;
    private int start;

    CircleString(String string) {
        this.string = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            this.string[i] = string.charAt(i);
        }
        start = 0;
    }

    private CircleString(char[] string, int start){
        this.string = string;
        this.start = start;
    }

    CircleString shift(){
        int newStart = start - 1;
        if (newStart == -1) newStart = string.length - 1;
        return new CircleString(string, newStart);
    }

    int length(){
        return string.length;
    }


    public Iterator<Character> iterator(){
        return new Iterator<Character>() {
            private int index = start;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < string.length;
            }

            @Override
            public Character next() {
                count ++;
                char c = string[index++];
                if (index == string.length)index = 0;
                return c;
            }
        };
    }

    @Override
    public int compareTo(@NotNull CircleString o) {
        return 0;
    }
}