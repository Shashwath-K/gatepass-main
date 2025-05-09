package in.snyce.gatepass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // HTTP security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/gatepass", "/api/gatepass/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());

        return http.build();
    }

    // In-memory user definition
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .roles("ADMIN")
                        .build()
        );
    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Utility to fetch current user details
    @Bean
    public CurrentUserDetails currentUserDetails() {
        return new CurrentUserDetails();
    }

    public static class CurrentUserDetails {

        // Simulate a userId for in-memory user setup
        private static final int HARDCODED_USER_ID = 101;

        public String getUsername() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User user) {
                return user.getUsername();
            }
            return null;
        }

        public int getUserId() {
            return HARDCODED_USER_ID; // Replace with DB lookup if using persistent users
        }

        public int getRequestorId() {
            return HARDCODED_USER_ID;
        }

        public String getRequestorName() {
            return getUsername(); // or map to a full name if needed
        }
    }
}
