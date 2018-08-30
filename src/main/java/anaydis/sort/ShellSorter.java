package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter {

    private static int[] generateSequence(int size){
        //1, 8, 23, 77, 281...
        int maxi = 1;

        //calculate max valid gap.
        while ( gap(maxi+1) < size){
            maxi++;
        }

        //build array with gaps values
        int[] gaps = new int[maxi + 1];
        for (int j = maxi; j > 0 ; j--) {
            gaps[maxi - j] = gap(j);
        }
        gaps[gaps.length - 1] = 1;
        return gaps;
    }

    private static int gap(int k){
        // Sedgewick, 1986
        return (int) (Math.pow(4, k) + 3 * Math.pow(2, k-1) + 1);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        HSorter hSorter = (HSorter) new SorterProviderImp().getSorterForType(SorterType.H);
        int[] gaps = generateSequence(list.size());
        for (int gap : gaps) {
            hSorter.sort(comparator, list, gap);
        }
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.SHELL;
    }

}
