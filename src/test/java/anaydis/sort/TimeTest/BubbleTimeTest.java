package anaydis.sort.TimeTest;

import anaydis.sort.SorterType;

import java.util.List;

public class BubbleTimeTest extends AbstractTimeTester {


    @Override
    SorterType getType() {
        return SorterType.BUBBLE;
    }

    @Override
    public List<Integer> worstCaseDataSet(int n) {
        return createIntegerDataSetGenerator().createDescending(n);
    }

    @Override
    public List<Integer> bestCaseDataSet(int n) {
        return integerDataSetGenerator.createAscending(n);
    }

}
