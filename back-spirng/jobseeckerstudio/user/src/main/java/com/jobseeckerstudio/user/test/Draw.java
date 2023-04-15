package com.jobseeckerstudio.user.test;
import java.util.function.Function;
public class Draw<T, R> {
    private T input;

    private Draw(T input) {
        this.input = input;
    }

    public static <T, R> Draw<T, R> init(T input) {
        return new Draw<>(input);
    }

    public <V> Draw<V, R> process(Function<T, V> function) {
        V intermediateResult = function.apply(input);
        return new Draw<>(intermediateResult);
    }

    public R end(Function<T, R> function) {
        return function.apply(input);
    }
}

