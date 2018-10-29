package anaydis.search;

import org.jetbrains.annotations.NotNull;

public class TSTMapTest extends AbstractTrieTest {

    @NotNull
    @Override
    Trie<String> getNewInstance() {
        return new TSTMap<>();
    }
}