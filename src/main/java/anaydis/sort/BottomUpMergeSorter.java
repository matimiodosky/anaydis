package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class BottomUpMergeSorter extends AbstractMergeSort {
    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        for (int middle = 1, lo = 0, hi = list.size() - 1; middle <= hi - lo;middle *= 2){
            final int middleX2 = middle * 2;
            for (int lo1 = lo;lo1 <= hi - middle; lo1 += middleX2){
                final  int hi1 = Math.min(lo1 - lo + middleX2 - 1, hi);
                merge(comparator, list, lo1, lo1 + middle - 1, hi1);
            }
        }

    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.MERGE_BOTTOM_UP;
    }
}
