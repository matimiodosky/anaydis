package anaydis.sort.TimeTest;

import anaydis.sort.SorterType;

import java.util.List;

public class SelectionTimeTest extends AbstractTimeTest{
    @Override
    SorterType getType() {
        return SorterType.SELECTION;
    }

    @Override
    public List<Integer> worstCaseDataSet(int n) {
        return integerDataSetGenerator.createDescending(n);
    }

    @Override
    public List<Integer> bestCaseDataSet(int n) {
        return integerDataSetGenerator.createAscending(n);
    }
}
