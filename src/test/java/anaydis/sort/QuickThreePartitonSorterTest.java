package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

public class QuickThreePartitonSorterTest extends SorterTest {

    @NotNull
    @Override
    SorterType getType() {
        return SorterType.QUICK_THREE_PARTITION;
    }
}
