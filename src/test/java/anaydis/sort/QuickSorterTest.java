package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class QuickSorterTest extends SorterTest{

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.QUICK;
    }
}
