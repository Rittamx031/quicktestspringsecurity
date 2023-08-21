package springsecurity.quicktestspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import springsecurity.quicktestspringsecurity.repo.KhachHangRepo;
import springsecurity.quicktestspringsecurity.repo.NhanVienRepo;
import springsecurity.quicktestspringsecurity.service.KhachHangInfoService;
import springsecurity.quicktestspringsecurity.service.NhanVienInfoService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

    @Autowired
    static NhanVienRepo nvifrepository;

    static NhanVienInfoService nhanVienServer() {
        return new NhanVienInfoService(nvifrepository);
    }

    @Bean
    static AuthenticationProvider authenticationNVProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(nhanVienServer());
        authenticationProvider.setPasswordEncoder(passwordEncoder2());
        return authenticationProvider;
    }

    @Autowired
    static KhachHangRepo khifrepository;

    static KhachHangInfoService KhachHangServer() {
        return new KhachHangInfoService(khifrepository);
    }

    @Bean
    static JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    static AuthenticationProvider authenticationKHProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(KhachHangServer());
        authenticationProvider.setPasswordEncoder(passwordEncoder2());
        return authenticationProvider;
    }

    // mã hóa mật khẩu
    @Bean(name = "passwordEncoder2")
    static PasswordEncoder passwordEncoder2() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "passwordEncoder")
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter {
        @Bean
        SecurityFilterChain filterChainApp1(HttpSecurity http) throws Exception {
            // http.antMatcher("/admin*")
            // .authorizeRequests()
            // .anyRequest()
            // .hasRole("ADMIN")

            // .and()
            // .formLogin()
            // .loginPage("/loginAdmin")
            // .loginProcessingUrl("/admin_login")
            // .failureUrl("/loginAdmin?error=loginError")
            // .defaultSuccessUrl("/adminPage")

            // .and()
            // .logout()
            // .logoutUrl("/admin_logout")
            // .logoutSuccessUrl("/protectedLinks")
            // .deleteCookies("JSESSIONID")

            // .and()
            // .exceptionHandling()
            // .accessDeniedPage("/403")

            // .and()
            // .csrf().disable();
            http
                    .authorizeHttpRequests((authorize) -> {
                        authorize.anyRequest().permitAll();
                    })
                    .csrf(AbstractHttpConfigurer::disable)
                    .httpBasic(Customizer.withDefaults())
                    .authenticationProvider(authenticationNVProvider());
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }

    @Configuration
    @Order(2)
    public static class App2ConfigurationAdapter {

        @Bean
        SecurityFilterChain filterChainApp2(HttpSecurity http) throws Exception {
            // http.antMatcher("/admin*")
            // .authorizeRequests()
            // .anyRequest()
            // .hasRole("ADMIN")

            // .and()
            // .formLogin()
            // .loginPage("/loginAdmin")
            // .loginProcessingUrl("/admin_login")
            // .failureUrl("/loginAdmin?error=loginError")
            // .defaultSuccessUrl("/adminPage")

            // .and()
            // .logout()
            // .logoutUrl("/admin_logout")
            // .logoutSuccessUrl("/protectedLinks")
            // .deleteCookies("JSESSIONID")

            // .and()
            // .exceptionHandling()
            // .accessDeniedPage("/403")

            // .and()
            // .csrf().disable();
            http
                    .authorizeHttpRequests((authorize) -> {
                        authorize.anyRequest().permitAll();
                    })
                    .csrf(AbstractHttpConfigurer::disable)
                    .httpBasic(Customizer.withDefaults())
                    .authenticationProvider(authenticationKHProvider());
            http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }
}
