package hotelService.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable CSRF since you're using JWT and REST
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/**", "/login", "/register", "/public/**").permitAll() // allow login without auth
                        .anyRequest().authenticated()
                )
                .formLogin().disable()   // disable Spring's default login page
                .httpBasic().disable()   // disable basic auth if not needed
                .logout().disable();     // disable logout (since JWT handles it)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // can be removed if you're validating via DB/loginService
        return new InMemoryUserDetailsManager();
    }
}
