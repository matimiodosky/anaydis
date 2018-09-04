package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class QuickThreePartitionSorterTest extends SorterTest {

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.QUICK_THREE_PARTITION;
    }
}
