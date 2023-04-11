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
import java.util.function.Consumer;
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

