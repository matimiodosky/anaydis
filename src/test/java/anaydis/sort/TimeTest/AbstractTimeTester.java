package anaydis.sort.TimeTest;

import anaydis.sort.Sorter;
import anaydis.sort.SorterType;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

abstract public class AbstractTimeTester {

    DataSetGenerator<Integer> integerDataSetGenerator = createIntegerDataSetGenerator();

    abstract SorterType getType();

    DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    private SorterProvider getSorterProvider() {
        return new anaydis.sort.SorterProvider();
    }

    public abstract List<Integer> worstCaseDataSet(int n);

     private List<Integer> averageCaseDataSet(int n){
        return integerDataSetGenerator.createRandom(n);
    }

    public abstract List<Integer> bestCaseDataSet(int n);

     @Test
    public void timeTest(){
         Sorter sorter = getSorterProvider().getSorterForType(getType());

         int[] ns = {100, 1000, 5000, 10000, 50000};
         for (int n : ns) {
             System.out.println(n);
             System.out.println(getType().toString());
             System.out.println("worst: ");
             List<Integer> dataSet = worstCaseDataSet(n);
             long init = System.currentTimeMillis();
             sorter.sort(Comparator.naturalOrder(), dataSet);
             System.out.println(System.currentTimeMillis() - init);

             System.out.println("average: ");
             dataSet = averageCaseDataSet(n);
             init = System.currentTimeMillis();
             sorter.sort(Comparator.naturalOrder(), dataSet);
             System.out.println(System.currentTimeMillis() - init);

             System.out.println("best: ");
             dataSet = bestCaseDataSet(n);
             init = System.currentTimeMillis();
             sorter.sort(Comparator.naturalOrder(), dataSet);
             System.out.println(System.currentTimeMillis() - init);
         }
     }


}
