package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.gui.SorterListener;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void listenersTest(){
        AbstractSorter sorter = (AbstractSorter) SorterProviderImpl.getInstance().getSorterForType(getType());
        SorterListener sorterListener = new SorterListener() {
            @Override
            public void box(int from, int to) {

            }

            @Override
            public void copy(int from, int to, boolean copyToAux) {

            }

            @Override
            public void equals(int i, int j) {

            }

            @Override
            public void greater(int i, int j) {

            }

            @Override
            public void swap(int i, int j) {

            }
        };
        sorter.addSorterListener(sorterListener);
        sorter.notifySwap(0, 0);
        sorter.notifyLess(0, 0);
        sorter.notifyEquals(0, 0);
        sorter.notifyCopy(0, 0, true);
        assertEquals(1, sorter.getSortersListeners().size());
        sorter.removeSorterListener(sorterListener);
}

    @NotNull
    abstract SorterType getType();

}
