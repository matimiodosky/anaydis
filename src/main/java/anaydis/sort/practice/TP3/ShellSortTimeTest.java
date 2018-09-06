package anaydis.sort.practice.TP3;

import anaydis.sort.ShellSorter;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

class ShellSortTimeTest {

    private static void testSequence(@NotNull DataSetGenerator<Integer> dataSetGenerator, @NotNull int[] sequence, int[] ns){

        ShellSorter sorter = (ShellSorter) SorterProviderImpl.getInstance().getSorterForType(SorterType.SHELL);
        for (int n:ns){
            List<Integer> list = dataSetGenerator.createRandom(n);
            long init = System.currentTimeMillis();
            sorter.sort(Comparator.naturalOrder(), list, sequence);
            System.out.print(System.currentTimeMillis() - init + ",");
        }
    }

    public static void main(String[] args) {
        int[] sequence1 = {1, 8, 23, 77, 281, 1073, 4193, 15577};
        int[] sequence2 = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841};
        int[] ns = {100, 1000, 10000};
        DataSetGenerator<Integer> dataSetGenerator = new IntegerDataSetGenerator();
        testSequence(dataSetGenerator, sequence1, ns);
        testSequence(dataSetGenerator, sequence2, ns);
    }

}
