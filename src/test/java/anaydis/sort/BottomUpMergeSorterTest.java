package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class BottomUpMergeSorterTest extends SorterTest{

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.MERGE_BOTTOM_UP;
    }

}
