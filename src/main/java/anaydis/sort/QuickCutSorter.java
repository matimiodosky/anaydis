package anaydis.sort;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends QuickSorter {

    private static final int M = 9;

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        quickSort(comparator, list, 0 , list.size() -1 );
        SorterProviderImpl.getInstance().
                getSorterForType(SorterType.INSERTION).sort(comparator, list);
    }

    private <T> void quickSort(Comparator<T> comparator, List<T> list, int l, int r) {
        if(r - l <= M) return;
        int i = partition(list, comparator, l, r);
        quickSort(comparator, list, l, i -1);
        quickSort(comparator, list, i+1, r);
    }


    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.QUICK_CUT;
    }

}
