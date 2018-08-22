package anaydis.sort;


import anaydis.sort.data.FullNameDataSetGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FullNameSortingTest {

    private FullNameDataSetGenerator generator = new FullNameDataSetGenerator();
    /**
     * Insertion sorter selected for its performance capabilities over the other two implemented sorters.
     */
    private Sorter sorter = new SorterProvider().getSorterForType(SorterType.INSERTION);
    private Comparator<FullName> lastNameComparator = generator.lastNameComparator();
    private Comparator<FullName> firstNameComparator = generator.firstNameComparator();
    private static final int size = 73;

    @Test
    public void test() {

        List<FullName> original = generator.createRandom(size);
        List<FullName> sorted = new ArrayList<>(original);

        sorter.sort(lastNameComparator, original);
        sorter.sort(firstNameComparator, original);

        sorted.sort(lastNameComparator);
        sorted.sort(firstNameComparator);

        assertThat(original).usingElementComparator(generator.getComparator()).containsExactlyElementsOf(sorted);
    }



}
