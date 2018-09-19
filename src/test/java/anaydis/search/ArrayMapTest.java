package anaydis.search;

public class ArrayMapTest extends AbstractMapTest{


    @Override
    Map<Integer, String> getNewInstance() {
        return new ArrayMap<>(Integer::compareTo);
    }
}