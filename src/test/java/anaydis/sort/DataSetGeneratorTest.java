package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * Data set generator test
 */

public class DataSetGeneratorTest extends AbstractDataSetGeneratorTest {

    @NotNull
    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @NotNull
    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testExceededSize(){
        createStringDataSetGenerator().createAscending(1000);
    }
}
