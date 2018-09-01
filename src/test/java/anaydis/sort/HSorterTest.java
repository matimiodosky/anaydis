package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class HSorterTest{

    private final HSorter sorter = (HSorter) SorterProviderImpl.getInstance().getSorterForType(SorterType.H);

    @NotNull
    private DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @NotNull
    private DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Test
    public void testIntegerDataSet(){
        //testAscending
        testDataSet(createIntegerDataSetGenerator().createAscending(30));

        //testDescending
        testDataSet(createIntegerDataSetGenerator().createDescending(30));

        //testRandom
        testDataSet(createIntegerDataSetGenerator().createRandom(30));
    }

    @Test
    public void testStringDataSet(){
        //testAscending
        testDataSet(createStringDataSetGenerator().createAscending(30));

        //testDescending
        testDataSet(createStringDataSetGenerator().createDescending(30));

        //testRandom
        testDataSet(createStringDataSetGenerator().createRandom(30));

    }
    private <T extends Comparable<T>> void testDataSet(@NotNull List<T> dataSet) {
        sorter.sort(Comparator.naturalOrder(), dataSet);
        int h = sorter.getH();
        for (int i = 0; i < dataSet.size(); i++) {
            if(i + h + 1 < dataSet.size()) {
                assertTrue(dataSet.get(i).compareTo(dataSet.get(i + h)) <= 0);
            }
        }
    }
}
