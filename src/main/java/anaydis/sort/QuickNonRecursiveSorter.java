package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QuickNonRecursiveSorter extends QuickSorter {

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list){
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(list.size() - 1);
        stack.push(0);
        while (!stack.isEmpty()){
            final int lo = stack.pop();
            final int hi = stack.pop();
            final int i = partition(list, comparator, lo, hi);
            if (i - 1 > lo){
                stack.push(i - 1);
                stack.push(lo);
            }
            if (hi > i + 1){
                stack.push(hi);
                stack.push(i +1);
            }
        }
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.QUICK_NON_RECURSIVE;
    }

}
