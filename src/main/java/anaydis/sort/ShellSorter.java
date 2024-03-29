package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter {

    @NotNull
    private final HSorter sorter;

    ShellSorter() {
        sorter = new HSorter();
    }

    @NotNull
    private static int[] generateDefaultSequence(int size){
        int maxi = 1;

        //calculate max valid H.
        while (gap(maxi+1) < size) maxi++;

        //build array with H values
        int[] gaps = new int[maxi + 1];
        for (int j = maxi; j > 0 ; j--) gaps[maxi - j] = gap(j);
        gaps[gaps.length - 1] = 1;
        return gaps;
    }

    private static int gap(int k){
        // Sedgewick, 1986
        //todo replace with the best
        return (int) (Math.pow(4, k) + 3 * Math.pow(2, k-1) + 1);
    }

    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        super.addSorterListener(listener);
        sorter.addSorterListener(listener);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
       sort(comparator, list, generateDefaultSequence(list.size()));
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, @NotNull int[] gaps){

        for (int gap : gaps) {
            if(gap < list.size()) {
                sorter.sort(comparator, list, gap);
            }
        }

    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.SHELL;
    }

}
