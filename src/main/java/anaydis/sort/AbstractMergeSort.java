package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

abstract class AbstractMergeSort extends AbstractSorter {

    <T> void merge(@NotNull Comparator<T> comparator, @NotNull List<T> list, int low, int middle, int hi){

        int i = low, j = middle + 1, k = low;
        List<T> original = new ArrayList<>();

        for (int l = 0; l < list.size(); l++) {
            copy(original, l, list, l);
        }

        while (i <= middle && j <= hi) {

            if (less(comparator, original, i, j)) {
                list.set(k++, original.get(i++));
            } else {
                list.set(k++, original.get(j++));
            }
        }

        if (i <= middle) {
            while (i <= middle) {
                list.set(k++, original.get(i++));
            }
        }

        if (j <= hi) {
            while (j <= hi) {
                list.set(k++, original.get(j++));
            }
        }

    }

}
