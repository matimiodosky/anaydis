package anaydis.sort.data;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class IntegerDataSetGenerator implements DataSetGenerator<Integer> {

    private static final int RANDOM_LIMIT = 100000;
    @NotNull
    @Override
    public List<Integer> createAscending(int length) {
        if (length <= 0) throw new IllegalArgumentException("Invalid length for data set: " + length);
        List<Integer> integerList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            integerList.add(i);
        }
        return integerList;
    }

    @NotNull
    @Override
    public List<Integer> createDescending(int length) {
        if (length <= 0) throw new IllegalArgumentException("Invalid length for data set: " + length);
        List<Integer> integerList = new ArrayList<>(length);
        for (int i = length; i > 0; i--) {
            integerList.add(i);
        }
        return integerList;
    }

    @NotNull
    @Override
    public List<Integer> createRandom(int length) {
        List<Integer> integerList = new ArrayList<>(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            integerList.add(random.nextInt(length));
        }
        return integerList;
    }

    @NotNull
    @Override
    public Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }

}
