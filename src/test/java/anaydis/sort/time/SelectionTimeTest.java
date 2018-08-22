package anaydis.sort.time;

import anaydis.sort.SorterType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SelectionTimeTest extends AbstractTimeTest{
    @NotNull
    @Override
    SorterType getType() {
        return SorterType.SELECTION;
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
