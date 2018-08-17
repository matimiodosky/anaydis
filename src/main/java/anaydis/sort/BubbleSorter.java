package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

class BubbleSorter extends AbstractSorter {

    BubbleSorter(){
        super(SorterType.BUBBLE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {

        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = n - 1; j > i; j--) {
                compareAndExchange(comparator, list, j, j-1);
            }
        }
    }
}
