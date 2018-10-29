package anaydis.search;

import org.jetbrains.annotations.NotNull;

public class ArrayMapTest extends AbstractMapTest{


    @NotNull
    @Override
    Map<String, String> getNewInstance() {
        return new ArrayMap<>(String::compareTo);
    }
}