package anaydis.sort;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSorterTest extends SorterTest {

    @Override
    SorterType getType() {
        return SorterType.INSERTION;
    }
}