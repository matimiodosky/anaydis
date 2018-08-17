package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSorterTest extends SorterTest {

    @Override
    SorterType getType() {
        return SorterType.BUBBLE;
    }
}