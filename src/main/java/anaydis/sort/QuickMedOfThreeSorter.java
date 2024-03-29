package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickMedOfThreeSorter extends QuickSorter{

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size() - 1);
    }


    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if (hi <= lo) return;
        exchange(list, (lo + hi)/2, hi - 1);
        compareAndExchange(comparator, list, lo, hi-1);
        compareAndExchange(comparator, list, lo, hi);
        compareAndExchange(comparator, list, hi -1, hi);

        int i = partition(list, comparator, lo, hi);
        sort(comparator, list, lo, i-1);
        sort(comparator, list, i+1, hi);
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.QUICK_MED_OF_THREE;
    }

}
