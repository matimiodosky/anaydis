package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Sorter tests should subclass this abstract implementation
 */
public abstract class SorterTest extends AbstractSorterTest {

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override
    protected SorterProvider getSorterProvider() {
        return new anaydis.sort.SorterProvider();
    }

    @Test
    public void StringTestSorter(){
       testSorter(createStringDataSetGenerator(), getType(), 120);
    }

    @Test
    public void IntegerTestSorter(){
        testSorter(createIntegerDataSetGenerator(), getType(), 500);
    }

    abstract SorterType getType();
}
