package anaydis.sort.data;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
        if (length <= 0) throw new IllegalArgumentException("Invalid length for data set: " + length);
        List<Integer> integerList = new ArrayList<>(length);
        for (int i = length; i > 0; i--) {
            integerList.add((int) (Math.random() * RANDOM_LIMIT));
        }
        return integerList;
    }

    @NotNull
    @Override
    public Comparator<Integer> getComparator() {
        return Comparator.naturalOrder();
    }

}
