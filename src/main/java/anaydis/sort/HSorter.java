package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class HSorter extends AbstractSorter{

    private int h;

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, list.size() / 5);
    }

    int getH() {
        return h;
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.H;
    }

    public <T> void sort(Comparator<T> comparator, List<T> list, int h) {
        this.h = h;
        int n  = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j -= h) {
                if(j-h >= 0 && less(comparator, list, j, j-h)){
                    exchange(list, j, j-h);
                }else {
                    break;
                }
            }
        }

    }
}
