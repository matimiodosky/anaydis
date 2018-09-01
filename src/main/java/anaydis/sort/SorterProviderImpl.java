package anaydis.sort;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;
import java.util.EnumMap;
import java.util.Map;

public class SorterProviderImpl implements anaydis.sort.provider.SorterProvider {

    @NotNull
    private final Map<SorterType, Sorter> sorters;

    private static SorterProvider instance;

    public static SorterProvider getInstance(){

        if(instance == null){
            instance = new SorterProviderImpl();
        }
        return instance;
    }

    public SorterProviderImpl(){

        sorters = new EnumMap<>(SorterType.class);

        BubbleSorter bubbleSorter = new BubbleSorter();
        sorters.put(bubbleSorter.getType(), bubbleSorter);

        SelectionSorter selectionSorter = new SelectionSorter();
        sorters.put(selectionSorter.getType(), selectionSorter);

        InsertionSorter insertionSorter  = new InsertionSorter();
        sorters.put(insertionSorter.getType(), insertionSorter);

        HSorter hSorter = new HSorter();
        sorters.put(hSorter.getType(), hSorter);

        ShellSorter shellSorter = new ShellSorter();
        sorters.put(shellSorter.getType(), shellSorter);

        QuickSorter quickSorter = new QuickSorter();
        sorters.put(quickSorter.getType(), quickSorter);

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
