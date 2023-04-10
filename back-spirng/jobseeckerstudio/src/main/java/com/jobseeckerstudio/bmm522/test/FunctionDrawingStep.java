package com.jobseeckerstudio.bmm522.test;

import java.util.function.Function;

public class FunctionDrawingStep<T, R> implements DrawingStep<T, R> {
    private final Function<T, R> function;

    public FunctionDrawingStep(Function<T, R> function) {
        this.function = function;
    }

    @Override
    public R apply(Object input) {
        return function.apply(input);
    }
}
