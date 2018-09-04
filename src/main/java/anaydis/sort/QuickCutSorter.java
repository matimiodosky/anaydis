package anaydis.sort;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends QuickSorter {

    private static final int M = 9;
    @NotNull
    private final InsertionSorter insertionSorter;


    QuickCutSorter() {
        insertionSorter = new InsertionSorter();
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        super.addSorterListener(listener);
        insertionSorter.addSorterListener(listener);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        quickSort(comparator, list, 0 , list.size() -1 );
        insertionSorter.sort(comparator, list);
    }

    private <T> void quickSort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
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
