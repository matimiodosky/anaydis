package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class TopDownMergeSorter extends AbstractMergeSort {


    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size() - 1);

    }

    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(lo < hi) {
            int middle = lo / 2 + hi / 2;
            sort(comparator, list, lo, middle);
            sort(comparator, list, middle + 1, hi);
            merge(comparator, list, lo, middle, hi);
        }
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.MERGE;
    }
}
