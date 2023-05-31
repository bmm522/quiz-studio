//package quiz.application;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import quiz.properties.EnvProperties;
//
//@Component
//@RequiredArgsConstructor
//public class Runner implements CommandLineRunner {
//
//	private final InitFactory initFacade;
//
//	private final String runEnv = "run";
//
//	@Override
//	public void run(String... args) throws Exception {
//		if (runEnv.equals(EnvProperties.RUN_ENV)) {
//			initFacade.initData();
//		}
//
//	}
//}
