package anaydis.search;

public class TreeMapTest extends AbstractMapTest {

    @Override
    Map<Integer, String> getNewInstance() {
        return new RandomizedTreeMap<>(Integer::compareTo);
    }
}
