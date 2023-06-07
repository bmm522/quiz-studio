package quiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("https://www.quizstudio.site")
			.allowedMethods("*")
			.allowedHeaders("*")
			.allowCredentials(true)
			.exposedHeaders("Authorization")
			.exposedHeaders("RefreshToken");
	}
}
