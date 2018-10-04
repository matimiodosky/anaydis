package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class QuickNonRecursiveSorter extends QuickSorter {

//    @Override
//    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
//        Stack<Integer> stack = new Stack<>();
//        stack.push(0);
//        stack.push(list.size() -1);
//        int r, l;
//        while(!stack.empty()){
//            r = stack.pop();
//            l = stack.pop();
//
//            if (r <= l){
//                continue;
//            }
//
//            int i = partition(list, comparator, l, r);
//            if(i - l > r -i ){
//                stack.push(l);
//                stack.push(i -1);
//            }
//            stack.push( i + 1);
//            stack.push(r);
//            if (r - i >= i - l){
//                stack.push(l);
//                stack.push(i - 1);
//            }
//        }
//    }

    @Override
    public <T> void sort(Comparator<T> comparator, List<T> list){
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
