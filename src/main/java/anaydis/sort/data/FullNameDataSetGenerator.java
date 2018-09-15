package anaydis.sort.data;

import anaydis.sort.FullName;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class FullNameDataSetGenerator implements DataSetGenerator<FullName> {

    @NotNull
    private final List<String> dictionary;

    public FullNameDataSetGenerator(){
        dictionary = initDictionary();
    }

    @NotNull
    @Override
    public List<FullName> createAscending(int length) {
        List<FullName> dataSet = createRandom(length);
        dataSet.sort(lastNameComparator());
        return dataSet;
    }

    @NotNull
    @Override
    public List<FullName> createDescending(int length) {
        List<FullName> dataSet = createRandom(length);
        dataSet.sort(lastNameComparator().reversed());
        return dataSet;
    }

    @NotNull
    @Override
    public List<FullName> createRandom(int length) {
        List<FullName> list = new ArrayList<>(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            list.add(new FullName(dictionary.get(random.nextInt(dictionary.size())),dictionary.get(random.nextInt(dictionary.size()))));
        }
        return list;
    }

    public static Comparator<FullName> lastNameComparator(){

        return Comparator.comparing(FullName::getLastName);

    }

    public static Comparator<FullName> firstNameComparator(){

        return Comparator.comparing(FullName::getFirstName);

    }

    /**
     * @return default comparator: lastName + firstName
     */
    @NotNull
    @Override
    public Comparator<FullName> getComparator() {
        return Comparator.comparing(o -> (o.getLastName() + o.getFirstName()));
    }

    private List<String> initDictionary() {
        return Arrays.asList("ALICE", "AMANDA",
                "AMY", "ANDREA", "ANGELA", "ANN", "ANNA", "ANNE", "ANNIE",
                "ASHLEY", "BARBARA", "BETTY", "BEVERLY", "BONNIE", "BRENDA",
                "CARMEN", "CAROL", "CAROLYN", "CATHERINE", "CHERYL",
                "CHRISTINA", "CHRISTINE", "CINDY", "CONNIE", "CRYSTAL",
                "CYNTHIA", "DAWN", "DEBORAH", "DEBRA", "DENISE", "DIANA",
                "DIANE", "DONNA", "DORIS", "DOROTHY", "EDITH", "EDNA",
                "ELIZABETH", "EMILY", "EVELYN", "FLORENCE", "FRANCES",
                "GLADYS", "GLORIA", "GRACE", "HEATHER", "HELEN", "IRENE",
                "JACQUELINE", "JANE", "JANET", "JANICE", "JEAN", "JENNIFER",
                "JESSICA", "JOAN", "JOYCE", "JUDITH", "JUDY", "JULIA", "JULIE",
                "KAREN", "KATHERINE", "KATHLEEN", "KATHRYN", "KATHY", "KELLY",
                "KIM", "KIMBERLY", "LAURA", "LILLIAN", "LINDA", "LISA", "LOIS",
                "LORI", "LOUISE", "MARGARET", "MARIA", "MARIE", "MARILYN",
                "MARTHA", "MARY", "MELISSA", "MICHELLE", "MILDRED", "NANCY",
                "NICOLE", "NORMA", "PABLIUS", "PAMELA", "PATRICIA", "PAULA", "PEDRO",
                "PEGGY", "PHYLLIS", "RACHEL", "REBECCA", "RITA", "ROBIN", "ROSA",
                "ROSE", "RUBY", "RUTH", "SANDRA", "SARA", "SARAH", "SHARON", "SHIRLEY",
                "STEPHANIE", "SUSAN", "TAMMY", "TERESA", "THERESA", "TIFFANY",
                "TINA", "TRACY", "VICTORIA", "VIRGINIA", "WANDA", "WENDY");
    }
}
