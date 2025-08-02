import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .cors().and()
                .csrf().disable()
                .authorizeExchange()
                // Permit all OPTIONS requests (for CORS preflight)
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                // Permit /ping endpoint without authentication
                .pathMatchers("/ping").permitAll()
                // Secure all other endpoints
                .anyExchange().authenticated()
                .and()
                .build();
    }
}
