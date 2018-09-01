package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class QuickCutSorterTest extends SorterTest {
    @NotNull
    @Override
    SorterType getType() {
       return SorterType.QUICK_CUT;
    }
}
