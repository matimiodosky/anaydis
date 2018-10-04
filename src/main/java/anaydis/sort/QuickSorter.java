package anaydis.sort;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class QuickSorter extends AbstractSorter {

  <T>  int partition(@NotNull List<T> list, @NotNull Comparator<T> comparator, int lo, int hi){
      int i = lo -1;
      int j = hi ;

      while (true){
          while( less(comparator, list, ++i, hi)) {
              if (i == hi) break;
          }
          while ( less(comparator, list, hi, --j)) {
              if (j == lo) break;
          }
          if (i >= j) {
              break;
          }

          exchange(list, i, j);
      }

      exchange(list, i, hi);
      return  i;
  }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size() - 1);
    }

    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if (hi <= lo) return;
        int i = partition(list, comparator, lo, hi);
        sort(comparator, list, lo, i-1);
        sort(comparator, list, i+1, hi);
    }

    @NotNull
    @Override
    public SorterType getType() {
        return SorterType.QUICK;
    }
}