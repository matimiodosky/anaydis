package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickThreePartitionSorter extends QuickSorter {


    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        sort(comparator, list, 0, list.size() -1);

    }

    private  <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi){
        //p & q are both pivots
        if (hi <= lo) return;
        int i = lo-1, j = hi, p = lo-1, q = hi, k;
        while (true){
            while (less(comparator, list, ++i, hi)){
                if(i == hi)break;
            }
            while (less(comparator, list, hi, --j)){
                if (j == lo) break;
            }
            if (i >= j)break;
            exchange(list, i, j);
            if (equals(comparator, list, i, hi)){
                exchange(list, ++p, i);
                if (equals(comparator,list, hi, j)){
                    exchange(list, --q, j);
                }
            }
        }

        exchange(list, i, hi);
        j = i - 1;
        i++;
        for (k = lo; k <= p;k++, j--){
            exchange(list, k, j);
        }
        for (k = hi-1; k >= q; k--, i++){
            exchange(list, k, i);
        }
        sort(comparator, list, lo, j);
        sort(comparator, list, i, hi);
    }
    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.QUICK_THREE_PARTITION;
    }
}
