package springsecurity.quicktestspringsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @Autowired
    NhanVienRepo nvifrepository;
    @Autowired
    KhachHangRepo khifrepository;

    // private final UserInfoRepository repository;

    // @Bean
    // UserDetailsService userDetailsService() {
    // return new UserInfoService(repository);
    // }

    NhanVienInfoService nhanVienServer() {
        return new NhanVienInfoService(nvifrepository);
    }

    KhachHangInfoService KhachHangServer() {
        return new KhachHangInfoService(khifrepository);
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // auth
    // .authenticationProvider(authenticationProviderTable1())
    // .authenticationProvider(authenticationProviderTable2());
    // }

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
                    authorize.anyRequest().permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationNVProvider())
                .authenticationProvider(authenticationKHProvider());
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationNVProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(nhanVienServer());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationProvider authenticationKHProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(KhachHangServer());
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
