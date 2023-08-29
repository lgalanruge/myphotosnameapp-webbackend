package co.com.myphotosnameapp.myphotoswebbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MyPhotosWebBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPhotosWebBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		final String url = "http://localhost:4200";
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/groupimage")
						.allowedOrigins(url);
				registry.addMapping("/imageset")
						.allowedOrigins(url);
				registry.addMapping("/ceremony")
						.allowedOrigins(url);
				registry.addMapping("/request")
						.allowedOrigins(url);
			}
		};
	}

}
