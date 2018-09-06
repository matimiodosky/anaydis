package anaydis.sort.practice.TP4;

import anaydis.sort.QuickSorter;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.gui.SorterListener;
import anaydis.sort.provider.SorterProvider;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class AllQuickSorterTimeTest {

    private static long testQuickSorter(QuickSorter quickSorter, int n){
        IntegerDataSetGenerator integerDataSetGenerator = new IntegerDataSetGenerator();

        long[] times = new long[3];
        for (int i = 0; i < 3; i++) {
            List<Integer> dataSet = integerDataSetGenerator.createRandom(n);
            long init = System.nanoTime();
            quickSorter.sort(Comparator.naturalOrder(), dataSet);
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

    private static class Counter implements SorterListener{

        int compares;
        int swaps;

        Counter() {
            compares = 0;
            swaps = 0;
        }

        @Override
        public void box(int i, int i1) {

        }

        @Override
        public void copy(int i, int i1, boolean b) {

        }

        @Override
        public void equals(int i, int i1) {
            compares++;
        }

        @Override
        public void greater(int i, int i1) {
            compares++;
        }

        @Override
        public void swap(int i, int i1) {
            swaps++;
        }
    }

    public static void main(String[] args) {

        SorterProvider sorterProvider = SorterProviderImpl.getInstance();
        List<QuickSorter> sorters = new ArrayList<>();
        sorters.add((QuickSorter) sorterProvider.getSorterForType(SorterType.QUICK));
        sorters.add((QuickSorter) sorterProvider.getSorterForType(SorterType.QUICK_CUT));
        sorters.add((QuickSorter) sorterProvider.getSorterForType(SorterType.QUICK_MED_OF_THREE));
        sorters.add((QuickSorter) sorterProvider.getSorterForType(SorterType.QUICK_NON_RECURSIVE));
        sorters.add((QuickSorter) sorterProvider.getSorterForType(SorterType.QUICK_THREE_PARTITION));

        int[] ns = {12500, 25000, 50000, 100000};
        for (int n : ns) {
            System.out.println("N: " + n);
            QuickSorter fastest = null;
            long minTime = Long.MAX_VALUE;
            for (QuickSorter sorter : sorters) {
                Counter counter = new Counter();
                sorter.addSorterListener(counter);
                try {
                    long time = testQuickSorter(sorter, n);
                    System.out.println(counter.compares + "," + counter.swaps + "," + time);
                    if (time < minTime) {
                        minTime = time;
                        fastest = sorter;
                    }
                } catch (StackOverflowError ignored) {
                }

            }
            assert fastest != null;
            System.out.println("Fastest:" + fastest.getType());
        }
    }

}
