package anaydis.sort;

import org.jetbrains.annotations.NotNull;


public class ShellSorterTest extends SorterTest {


    @NotNull
    @Override
    SorterType getType() {
        return SorterType.SHELL;
    }
}
