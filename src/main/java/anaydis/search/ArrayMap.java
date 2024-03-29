package anaydis.search;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayMap<K, V> implements Map<K, V> {

    @NotNull
    private final List<K> keys;
    @NotNull
    private final List<V> values;
    private final Comparator<K> comparator;

    public ArrayMap(Comparator<K> comparator) {
        keys = new ArrayList<>();
        values = new ArrayList<>();
        this.comparator = comparator;
    }

    private int find(K key, int lo, int hi){
        if(lo <= hi) {
            final int middle = (lo + hi) / 2;
            final int cmp = comparator.compare(key, keys.get(middle));
            if (cmp == 0) return middle;
            return cmp > 0 ? find(key, middle + 1, hi) : find(key, lo, middle - 1);
        }
        return - (lo + 1);
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(key, 0, keys.size() - 1) >= 0;
    }


    @Nullable
    @Override
    public V get(@NotNull K key) {
        final int index = find(key, 0, keys.size() - 1);
        return index >= 0 ? values.get(index) : null;
    }

    @Nullable
    @Override
    public V put(@NotNull K key, V value) {
        final int index = find(key, 0, keys.size()  -1);
        if(index >= 0){
            return values.set(index, value);
        }else {
            keys.add(-index - 1, key);
            values.add(-index - 1, value);
            return null;
        }
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @NotNull
    @Override
    public Iterator<K> keys() {
        return keys.iterator();
    }
}
