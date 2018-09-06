package anaydis.sort;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuickCutSorterTest extends SorterTest {
    @NotNull
    @Override
    SorterType getType() {
       return SorterType.QUICK_CUT;
    }

    @Test
    public void testM(){
        QuickCutSorter cutSorter = new QuickCutSorter();
        cutSorter.setM(10);
        assertEquals(10, cutSorter.getM());
    }
}
