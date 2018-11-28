package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;

public class TopDownMergeSorterTest extends SorterTest {

    @Test
    public void test_merge_works(){

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            listA.add(r.nextInt(200));
            listB.add(r.nextInt(200));
        }

        listB.remove(listB.size() -1);

        Collections.sort(listA);
        Collections.sort(listB);

        list.addAll(listA);
        list.addAll(listB);

        TopDownMergeSorter s = new TopDownMergeSorter();
        s.merge(Comparator.naturalOrder(), list, 0, 99 , list.size() - 1);

        for (int i = 0; i < list.size() - 1; i++) {
            assertTrue(list.get(i) <= list.get(i + 1));
        }
    }


    @NotNull
    @Override
    SorterType getType() {
        return SorterType.MERGE;
    }
}
