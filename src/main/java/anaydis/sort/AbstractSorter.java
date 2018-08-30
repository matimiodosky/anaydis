package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements Sorter, ObservableSorter {

    private final List<SorterListener> sorterListeners = new ArrayList<>();

    <T> boolean less(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j) {
        notifyLess(i, j);
        return comparator.compare(list.get(i), list.get(j)) < 0;
    }

    <T> void exchange(@NotNull List<T> list, int i, int j) {
        notifySwap(i, j);
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    <T> void compareAndExchange(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j) {
        if (less(comparator, list, i, j)) {
            exchange(list, i, j);
        }
    }

    private void notifySwap(int i, int j) {
        for (SorterListener sorterListener : sorterListeners) {
            sorterListener.swap(i, j);
        }
    }

    private void notifyLess(int j, int i) {
        for (SorterListener sorterListener : sorterListeners) {
            sorterListener.greater(i, j);
        }
    }

    //todo test addSorterListener and removeSorterListener
    @Override
    public void addSorterListener(@NotNull SorterListener listener) {
        sorterListeners.add(listener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener listener) {
        sorterListeners.remove(listener);
    }

}
