package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class QuickNonRecursiveSorter extends QuickSorter {

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(list.size() -1);
        int r, l;
        while(!stack.empty()){
            r = stack.pop();
            l = stack.pop();

            if (r <= l){
                continue;
            }

            int i = partition(list, comparator, l, r);
            if(i - l > r -i ){
                stack.push(l);
                stack.push(i -1);
            }
            stack.push( i + 1);
            stack.push(r);
            if (r - i >= i - l){
                stack.push(l);
                stack.push(i - 1);
            }
        }
    }

    @Override
    public SorterType getType() {
        return SorterType.QUICK_NON_RECURSIVE;
    }

}
