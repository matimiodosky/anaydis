package anaydis.sort.Provider;

import anaydis.sort.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class SorterProvider implements anaydis.sort.provider.SorterProvider {

    private Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

    public SorterProvider(){
        BubleSorter bubleSorter = new BubleSorter();
        sorters.put(bubleSorter.getType(), bubleSorter);

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
