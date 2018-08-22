package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class InsertionSorterTest extends SorterTest {

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.INSERTION;
    }
}