package com.jobseeckerstudio.user.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addContextCustomizers(
			context -> context.setCookieProcessor(new LegacyCookieProcessor()));
	}
}
