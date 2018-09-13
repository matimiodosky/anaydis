package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class BottomUpMergeSorter extends AbstractMergeSort {
    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.MERGE_BOTTOM_UP;
    }
}
