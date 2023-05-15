package quiz.application;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final InitFactory initFacade;
    @Override
    public void run(String... args) throws Exception {
        initFacade.initData();
    }
}
