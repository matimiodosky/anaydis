package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class InsertionSorter extends AbstractSorter {

    InsertionSorter(){
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        int n  = list.size();
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if(less(comparator, list, j, j-1)){
                    exchange(list, j, j-1);
                }else {
                    break;
                }
            }
        }
    }
}
