package anaydis.sort.time;

import anaydis.sort.SorterType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BubbleTimeTest extends AbstractTimeTest {


    @NotNull
    @Override
    SorterType getType() {
        return SorterType.BUBBLE;
    }

    @NotNull
    @Override
    public List<Integer> worstCaseDataSet(int n) {
        return createIntegerDataSetGenerator().createDescending(n);
    }

    @NotNull
    @Override
    public List<Integer> bestCaseDataSet(int n) {
        return integerDataSetGenerator.createAscending(n);
    }

}
