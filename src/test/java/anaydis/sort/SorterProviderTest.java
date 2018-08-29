package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import org.jetbrains.annotations.NotNull;

/**
 * Sorter provider test
 */
public class SorterProviderTest extends AbstractSorterProviderTest {

    @NotNull
    @Override protected SorterProvider createSorterProvider() {
        return new SorterProviderImp();
    }
}
