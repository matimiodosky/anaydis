package anaydis.search;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;


public class BinaryTrieMapTest extends AbstractMapTest{


    @NotNull
    @Override
    Map<String, String> getNewInstance() {
        return new BinaryTrieMap<>();
    }
}