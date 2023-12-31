package springsecurity.quicktestspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .authenticationProvider(authenticationNVProvider())
                .authenticationProvider(authenticationKHProvider())
                .build();
    }

    @Autowired
    NhanVienRepo nvifrepository;

    NhanVienInfoService nhanVienServer() {
        return new NhanVienInfoService(nvifrepository);
    }

    @Bean
    AuthenticationProvider authenticationNVProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(nhanVienServer());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    KhachHangRepo khifrepository;

    KhachHangInfoService KhachHangServer() {
        return new KhachHangInfoService(khifrepository);
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    AuthenticationProvider authenticationKHProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(KhachHangServer());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // mã hóa mật khẩu
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager)
            throws Exception {
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("khach-hang/singin").permitAll();
        })
                .authorizeHttpRequests((authorize) -> {
                    authorize.anyRequest().permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(authManager);
        return http.build();
    }

}
