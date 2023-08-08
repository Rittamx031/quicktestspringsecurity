package springsecurity.quicktestspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import springsecurity.quicktestspringsecurity.repo.UserInfoRepository;
import springsecurity.quicktestspringsecurity.service.UserInfoService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SpringSecurityConfig {
    private final UserInfoRepository repository;

    @Bean
    UserDetailsService userDetailsService() {
        return new UserInfoService(repository);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("api/v2/person/delete/**").authenticated();
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("api/v2/person/**").permitAll();
                })
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/user/new").permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    // @Bean
    // UserDetailsService userDetailsService() {

    // UserDetails ramesh = User.builder()
    // .username("ph22358")
    // .password(passwordEncoder().encode("ph22358"))
    // .roles("USER")
    // .build();

    // UserDetails admin = User.builder()
    // .username("admin")
    // .password(passwordEncoder().encode("admin"))
    // .roles("ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(ramesh, admin);
    // }

    @Bean
    static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
