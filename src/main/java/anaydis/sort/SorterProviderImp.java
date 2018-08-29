package anaydis.sort;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class SorterProviderImp implements anaydis.sort.provider.SorterProvider {

    @NotNull
    private final Map<SorterType, Sorter> sorters;

    public SorterProviderImp(){

        sorters = new EnumMap<>(SorterType.class);

        BubbleSorter bubbleSorter = new BubbleSorter();
        sorters.put(bubbleSorter.getType(), bubbleSorter);

        SelectionSorter selectionSorter = new SelectionSorter();
        sorters.put(selectionSorter.getType(), selectionSorter);

        InsertionSorter insertionSorter  = new InsertionSorter();
        sorters.put(insertionSorter.getType(), insertionSorter);
    }

    @NotNull
    @Override
    public Iterable<Sorter> getAllSorters() {
        return sorters.values();
    }

    @NotNull
    @Override
    public Sorter getSorterForType(@NotNull SorterType type) {
        Sorter sorter;
        sorter = sorters.get(type);
        if (sorter == null) throw new IllegalArgumentException("No implementation for such sorter: " + type);
        return sorter;
    }
}
