package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements Sorter {

    private final SorterType sorterType;

    AbstractSorter(SorterType sorterType){
        this.sorterType = sorterType;
    }


    @NotNull
    @Override
    public SorterType getType() {
        return sorterType;
    }

    <T> boolean less(Comparator<T> comparator, List<T> list,int i,int j){
        return comparator.compare(list.get(i), list.get(j)) < 0;
    }

    <T> void exchange(List<T> list, int i, int j){
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    <T> void compareAndExchange (Comparator<T> comparator, List<T> list, int i, int j) {
        if (less(comparator, list, i, j)){
            exchange(list, i, j);
        }
    }
}
