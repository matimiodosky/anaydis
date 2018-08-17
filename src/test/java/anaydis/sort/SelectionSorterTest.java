package anaydis.sort;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSorterTest extends SorterTest {

    @Override
    SorterType getType() {
        return SorterType.SELECTION;
    }
}