package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * Sorter tests should subclass this abstract implementation
 */

public abstract class SorterTest extends AbstractSorterTest {

    @NotNull
    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @NotNull
    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @NotNull
    @Override
    protected SorterProvider getSorterProvider() {
        return SorterProviderImpl.getInstance();
    }

    @Test
    public void stringTestSorter(){
       testSorter(createStringDataSetGenerator(), getType(), 120);
    }

    @Test
    public void integerTestSorter(){
        testSorter(createIntegerDataSetGenerator(), getType(), 500);
    }

    @NotNull
    abstract SorterType getType();

}
