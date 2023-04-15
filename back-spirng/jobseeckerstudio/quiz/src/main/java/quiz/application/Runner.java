package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final Init init;
    @Override
    public void run(String... args) throws Exception {
        init.initData();
    }
}
