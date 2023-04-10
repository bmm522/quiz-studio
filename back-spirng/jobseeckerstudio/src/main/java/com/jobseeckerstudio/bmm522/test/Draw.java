package com.jobseeckerstudio.bmm522.test;

import com.jobseeckerstudio.bmm522.user.controller.dto.CommonResponse;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
public class Draw<T, R> {

    private T input;
    private List<DrawingStep<?, ?>> steps;

    private Draw(T input) {
        this.input = input;
        this.steps = new ArrayList<>();
    }

    public static <T, R> Draw<T, R> start(T input) {
        return new Draw<>(input);
    }

    public <V> Draw<V, R> drawing(Function<T, V> function) {
        steps.add(new FunctionDrawingStep<>(function));
        V intermediateResult = function.apply(input);
        return new Draw<>(intermediateResult);
    }

    public R end(Function<T, R> function) {
        Object currentInput = input;
        for (DrawingStep<?, ?> step : steps) {
            currentInput = step.apply(currentInput);
        }
        return function.apply((T) currentInput);
    }

}


