package anaydis.sort.practice.TP4;

import anaydis.sort.QuickCutSorter;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.data.IntegerDataSetGenerator;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorterTimeTest {

    private static final QuickCutSorter sorter = (QuickCutSorter) SorterProviderImpl.getInstance().getSorterForType(SorterType.QUICK_CUT);

    private static long test(int n, int m) {
        IntegerDataSetGenerator dataSetGenerator = new IntegerDataSetGenerator();
        long[] times = new long[3];
        for (int i = 0; i < 3; i++) {
            List<Integer> dataSet = dataSetGenerator.createRandom(n);
            long init = System.nanoTime();
            sorter.setM(m);
            sorter.sort(Comparator.naturalOrder(), dataSet);
            times[i] = System.nanoTime() - init;
        }
        return average(times);
    }

    private static long average(long[] times) {
        long sum = 0;
        for (long time : times) {
            sum = +time;
        }
        return sum / times.length;
    }

    public static void main(String[] args) {

        for (int n = 3; n <= 6; n++) {
            long minTime = Long.MAX_VALUE;
            int minM = -1;
            for (int m = 5; m <= 25; m++) {
                long time = test((int) Math.pow(10, n), m);
                if (time < minTime){
                    minTime = time;
                    minM = m;
                }
            }
            System.out.println(minM);
        }

    }
}
