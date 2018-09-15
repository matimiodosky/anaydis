package anaydis.sort;


import anaydis.sort.data.FullNameDataSetGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FullNameSortingTest {

    private final FullNameDataSetGenerator generator = new FullNameDataSetGenerator();
    /**
     * Insertion sorter selected for its performance capabilities over the other two implemented sorters.
     */
    private final Sorter sorter = SorterProviderImpl.getInstance().getSorterForType(SorterType.INSERTION);
    private final Comparator<FullName> lastNameComparator = FullNameDataSetGenerator.lastNameComparator();
    private final Comparator<FullName> firstNameComparator = FullNameDataSetGenerator.firstNameComparator();
    private static final int size = 73;


    @Test
    public void testFullNameSorter(){

        testFromDataSet(generator.createRandom(size));
        testFromDataSet(generator.createAscending(size));
        testFromDataSet(generator.createDescending(size));

    }

    private void testFromDataSet(@NotNull List<FullName> dataSet) {

        List<FullName> sorted = new ArrayList<>(dataSet);

        sorter.sort(lastNameComparator, dataSet);
        sorter.sort(firstNameComparator, dataSet);

        sorted.sort(lastNameComparator);
        sorted.sort(firstNameComparator);

        assertThat(dataSet).usingElementComparator(generator.getComparator()).containsExactlyElementsOf(sorted);
    }

}
