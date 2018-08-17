package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class SelectionSorter extends AbstractSorter {

    SelectionSorter() {
        super(SorterType.SELECTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (less(comparator, list, min, j)){
                   min = j;
                }
            }
            exchange(list, i, min);
        }
    }
}