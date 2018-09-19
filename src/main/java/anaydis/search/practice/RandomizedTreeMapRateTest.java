package anaydis.search.practice;

import anaydis.search.RandomizedTreeMap;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;

import java.util.List;

public class RandomizedTreeMapRateTest {

    private static RandomizedTreeMap<Integer, Integer> randomizedTreeMap = new RandomizedTreeMap<>(Integer::compareTo);

    private static long averageTime(double p){
        randomizedTreeMap.clear();
        List<Integer> ints = new IntegerDataSetGenerator().createRandom(100000);

        long[] times = new long[3];

        for (int j = 0; j < 3; j++) {
            randomizedTreeMap.clear();
            long start = System.currentTimeMillis();

            for (int i = 0; i < ints.size(); i++) {
                randomizedTreeMap.put(ints.get(i), ints.get(i));
            }

            for (int i = ints.size() - 1; i >= 0; i--) {
                randomizedTreeMap.get(ints.get(i));
            }
            times[j] = System.currentTimeMillis() - start;
        }
        return (times[0] + times[1] + times[2])/3;
    }

    public static void main(String[] args) {
        double[] ps = {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.62, 0.64, 0.66, 0.68, 0.7, 0.8, 0.9, 1};

        long min = Long.MAX_VALUE;
        double minp = -1;
        for (double p : ps) {
            long time = averageTime(p);
            if (time < min){
                min = time;
                minp = p;
            }
            System.out.println("P = " + p + "->" + time);
        }
        System.out.println("Min time for p: " + min + " for p = " + minp);
        //fastest = 0.6
    }

}
