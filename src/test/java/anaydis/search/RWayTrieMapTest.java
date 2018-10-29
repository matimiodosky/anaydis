package anaydis.search;

import org.jetbrains.annotations.NotNull;

public class RWayTrieMapTest  extends AbstractTrieTest{


    @NotNull
    @Override
    Trie<String> getNewInstance() {
        return new RWayTrieMap<>();
    }
}
