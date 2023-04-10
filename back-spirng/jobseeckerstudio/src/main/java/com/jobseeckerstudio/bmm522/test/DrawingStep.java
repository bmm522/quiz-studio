package com.jobseeckerstudio.bmm522.test;

public interface DrawingStep<T, R> {
    R apply(Object input);
}
