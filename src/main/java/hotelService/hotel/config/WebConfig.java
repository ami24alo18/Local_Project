package hotelService.hotel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // or "*" to allow all origins
                .allowedMethods("GET", "POST", "DELETE") // specify the allowed HTTP methods
                .allowedHeaders("*") // allow all headers
                .allowCredentials(true) // allow credentials like cookies
                .maxAge(3600); // max age of the CORS options pre-flight request
    }
}
