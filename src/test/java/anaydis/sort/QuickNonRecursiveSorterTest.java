package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class QuickNonRecursiveSorterTest extends SorterTest{
    @NotNull
    @Override
    SorterType getType() {
        return SorterType.QUICK_NON_RECURSIVE;
    }
}
