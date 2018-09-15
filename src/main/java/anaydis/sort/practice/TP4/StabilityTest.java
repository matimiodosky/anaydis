package anaydis.sort.practice.TP4;

import anaydis.sort.Sorter;
import anaydis.sort.SorterProviderImpl;
import anaydis.sort.SorterType;
import anaydis.sort.data.FullNameDataSetGenerator;
import anaydis.sort.FullName;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class StabilityTest {


    @NotNull
    private static List<FullName> generateSet(){

        List<FullName> list = new ArrayList<>();
        list.add(new FullName("1", "A"));
        list.add(new FullName("1", "B"));
        list.add(new FullName("0", "C"));
        list.add(new FullName("2", "A"));
        list.add(new FullName("2", "B"));
        list.add(new FullName("3", "A"));
        list.add(new FullName("0", "D"));
        list.add(new FullName("0", "E"));
        list.add(new FullName("4", "A"));
        list.add(new FullName("3", "B"));
        list.add(new FullName("4", "B"));
        list.add(new FullName("5", "A"));
        list.add(new FullName("5", "B"));
        list.add(new FullName("0", "F"));
        return list;
    }

    private static void testStable(Sorter sorter, @NotNull List<FullName> set){
        System.out.println(set);
        sorter.sort(FullNameDataSetGenerator.firstNameComparator(), set);
        System.out.println(set);
    }

    public static void main(String[] args) {
        System.out.println("Quick: ");
        testStable(SorterProviderImpl.getInstance().getSorterForType(SorterType.QUICK), generateSet());
        System.out.println("Shell");
        testStable(SorterProviderImpl.getInstance().getSorterForType(SorterType.SHELL), generateSet());
    }

}
