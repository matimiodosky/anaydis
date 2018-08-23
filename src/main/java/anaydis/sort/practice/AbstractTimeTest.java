package anaydis.sort.practice;

import anaydis.sort.Sorter;
import anaydis.sort.SorterType;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

abstract public class AbstractTimeTest {

    final DataSetGenerator<Integer> integerDataSetGenerator = createIntegerDataSetGenerator();

    @NotNull
    abstract SorterType getType();

    @NotNull
    DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @NotNull
    private SorterProvider getSorterProvider() {
        return new anaydis.sort.SorterProvider();
    }

    @NotNull
    protected abstract List<Integer> worstCaseDataSet(int n);

    private List<Integer> averageCaseDataSet(int n){
        return integerDataSetGenerator.createRandom(n);
    }

    @NotNull
    protected abstract List<Integer> bestCaseDataSet(int n);

    private void testWorst(int n, @NotNull Sorter sorter){
         System.out.println(n);
         System.out.println(getType().toString());
         System.out.println("worst: ");

         List<Integer> dataSet = worstCaseDataSet(n);
         long init = System.currentTimeMillis();
         sorter.sort(Comparator.naturalOrder(), dataSet);
         System.out.println(System.currentTimeMillis() - init);
     }

    private void testAverage(int n, Sorter sorter) {
        System.out.println("average: ");
        List<Integer> dataSet = averageCaseDataSet(n);
        long init = System.currentTimeMillis();
        sorter.sort(Comparator.naturalOrder(), dataSet);
        System.out.println(System.currentTimeMillis() - init);
    }

    private void testBest(int n, @NotNull Sorter sorter){
        System.out.println("best: ");

        List<Integer> dataSet = bestCaseDataSet(n);
        long init = System.currentTimeMillis();
        sorter.sort(Comparator.naturalOrder(), dataSet);
        System.out.println(System.currentTimeMillis() - init);
    }

    /**
     * Test implemented to output execution timing.
     * Sorters tested for best case, average case and worst case.
     * All sorters have average case. If there is no worst or best
     * case, null is return from worstCaseDataSet(n) and bestCaseDataSet(n).
     * The test runs for every n in ns array.
     */

    public void timeTest(){

            Sorter sorter = getSorterProvider().getSorterForType(getType());

            int[] ns = {100, 1000, 5000, 10000, 50000};

            for (int n : ns) {
                testWorst(n, sorter);
                testAverage(n, sorter);
                testBest(n, sorter);
            }
     }

}
