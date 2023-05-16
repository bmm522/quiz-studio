package quiz.properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvProperties {

    @Value("${run.env}")
    private String runEnv;

    public static String RUN_ENV;

    @PostConstruct
    public void init() {
        RUN_ENV = runEnv;
    }
}
