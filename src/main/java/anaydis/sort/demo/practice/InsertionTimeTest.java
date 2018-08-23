package anaydis.sort.demo.practice;

import anaydis.sort.SorterType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InsertionTimeTest extends AbstractTimeTest{
    @NotNull
    @Override
    SorterType getType() {
        return SorterType.INSERTION;
    }

    @NotNull
    @Override
    public List<Integer> worstCaseDataSet(int n) {
        return integerDataSetGenerator.createDescending(n);
    }

    @NotNull
    @Override
    public List<Integer> bestCaseDataSet(int n) {
        return integerDataSetGenerator.createAscending(n);
    }
}
