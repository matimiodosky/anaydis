package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class HSorter extends AbstractSorter{

    private int h;

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        h = 3 ;
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

    int getH() {
        return h;
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.H;
    }
}
