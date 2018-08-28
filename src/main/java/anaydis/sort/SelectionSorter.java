package anaydis.sort;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

class SelectionSorter extends AbstractSorter {

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (less(comparator, list, j, min)){
                   min = j;
                }
            }
            exchange(list, i, min);
        }
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.SELECTION;
    }

}