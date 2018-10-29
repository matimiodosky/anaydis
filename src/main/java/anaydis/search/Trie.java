package anaydis.search;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

interface Trie<V> {

    @NotNull
    List<String> autocomplete(String key);

    @Nullable
    V get(@NotNull final String key);

    @Nullable
    V put(@NotNull final String key, final V value);

    int size();

    @NotNull
    Iterator<String> keys();

    boolean containsKey(String key);

    void clear();

}
