package com.course.coures.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	
	@Bean
	public WebMvcConfigurer corseConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
	 /*
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // Allow all paths
	                .allowedOrigins("http://localhost:3000")  // Allow React frontend
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow these HTTP methods
	                .allowedHeaders("*");  // Allow all headers
	                //.allowCredentials(true); // Uncomment if you're using cookies or Authorization header
	    }
	    
	      @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()  // Enable CORS support
            .and()
            .csrf().disable()  // Optional: disable CSRF for APIs
            .authorizeHttpRequests()
            .requestMatchers("/**").permitAll();  // Adjust as needed

        return http.build();
    }
	*/

}
