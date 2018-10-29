package anaydis.compression;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;

class CircleString implements Comparable<CircleString>{

    @NotNull
    final private char[] string;
    final private int start;
    final private int length;

    CircleString(char[] string) {
        this.string = string;
        this.length = string.length;
        this.start = 0;
    }

    private CircleString(char[] string, int start){
        if (string.length == 0 || start < 0)throw new IllegalArgumentException();
        this.string = string;
        this.start = start;
        this.length = string.length;
    }

    @NotNull
    CircleString shift(){
        int newStart = start - 1;
        if (newStart == -1) newStart = length - 1;
        return new CircleString(string, newStart);
    }

    int length(){
        return length;
    }

    @NotNull
    Iterator<Character> iterator(){
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
        if (o.length != this.length)throw new IllegalArgumentException("Only same length CircleString are comparable");
        final Iterator<Character> it1 = this.iterator(), it2 = o.iterator();
        int cmp = Character.compare(it1.next(), it2.next());
        while (cmp == 0 && it1.hasNext()){
            cmp = Character.compare(it1.next(), it2.next());
        }
        return cmp;
    }

    @NotNull
    @Override
    public String toString() {
        final StringBuilder stringBuilder  = new StringBuilder();
        final Iterator<Character> characterIterator = this.iterator();
        while (characterIterator.hasNext()){
            stringBuilder.append(characterIterator.next());
        }
        return stringBuilder.toString();
    }

    char last() {
        if (start == 0){
            return string[length - 1];
        }
        return string[start - 1];
    }

    int getStart() {
        return start;
    }
}