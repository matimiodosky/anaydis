package anaydis.sort;

import anaydis.sort.Example.FullName;
import anaydis.sort.data.FullNameDataSetGenerator;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FullNameSortingTest {

    private FullNameDataSetGenerator fullNameDataSetGenerator = new FullNameDataSetGenerator();
    private Sorter sorter = new SorterProvider().getSorterForType(SorterType.INSERTION);

    @Test
    public void testRandom(){

    }

    @Test
    public void testAscending(){

    }

    @Test
    public void testDescending(){

    }


}
