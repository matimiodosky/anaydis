package anaydis.sort;

import org.jetbrains.annotations.NotNull;

public class SelectionSorterTest extends SorterTest {

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.SELECTION;
    }
}