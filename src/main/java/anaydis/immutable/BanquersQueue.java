package anaydis.immutable;
import org.jetbrains.annotations.NotNull;

public class BanquersQueue<T> implements Queue<T> {

    private List<T> in, out;

    public BanquersQueue(@NotNull List<T> in, @NotNull List<T> out){
        this.in = in;
        this.out = out;
    }

    public BanquersQueue() {
        this.in = List.nil();
        this.out = List.nil();
    }

    @NotNull
    @Override
    public BanquersQueue<T> enqueue(@NotNull T value) {
        return new BanquersQueue<>(List.cons(value, in), out);
    }

    @NotNull
    @Override
    public Result<T> dequeue() {
        if (out.isEmpty()){
            List<T> out = in.reverse();
            return new Result<>(out.head(), new BanquersQueue<>(List.nil(), out.tail()));
        }
        return new Result<>(out.head(), new BanquersQueue<>(in, out.tail()));
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
