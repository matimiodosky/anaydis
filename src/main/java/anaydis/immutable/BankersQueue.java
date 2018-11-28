package anaydis.immutable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class BankersQueue<T> implements Queue<T> {

    private final List<T> in;
    private final List<T> out;

    public BankersQueue(@NotNull List<T> in, @NotNull List<T> out){
        this.in = in;
        this.out = out;
    }

    BankersQueue() {
        this.in = List.nil();
        this.out = List.nil();
    }

    @NotNull
    @Override
    public BankersQueue<T> enqueue(@NotNull T value) {
        return new BankersQueue<>(List.cons(value, in), out);
    }

    @NotNull
    @Override
    public Result<T> dequeue() {
        if (out.isEmpty()){
            List<T> out = in.reverse();
            return new Result<>(out.head(), new BankersQueue<>(List.nil(), out.tail()));
        }
        return new Result<>(out.head(), new BankersQueue<>(in, out.tail()));
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

}
