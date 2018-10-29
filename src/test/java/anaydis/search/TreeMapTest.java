package anaydis.search;

import org.jetbrains.annotations.NotNull;

public class TreeMapTest extends AbstractMapTest {

    @NotNull
    @Override
    Map<String, String > getNewInstance() {
        return new RandomizedTreeMap<>(String::compareTo);
    }
}
