// package springsecurity.quicktestspringsecurity.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class DefaultSecurityConfig {
//     @Bean
//     UserDetailsService userDetailsService() {
//         InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//         manager.createUser(
//                 User.builder().username("user").password("password").roles("USER").build());
//         return manager;
//     }

//     // @Bean
//     // UserDetailsService userDetailsService(PasswordEncoder encoder) {
//     // UserDetails admin = User.withUsername("hach")
//     // .password(encoder.encode("hacheery"))
//     // .roles("ADMIN")
//     // .build();
//     // UserDetails user = User.withUsername("user")
//     // .password(encoder.encode("pwd1"))
//     // .roles("USER")
//     // .build();
//     // return new InMemoryUserDetailsManager(admin, user);
//     // }

//     // @Bean
//     // PasswordEncoder passwordEncoder() {
//     // return new BCryptPasswordEncoder();
//     // }

//     @Bean
//     SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .authorizeRequests(authorize -> authorize
//                         .anyRequest().authenticated())
//                 .formLogin(withDefaults())
//                 .httpBasic(withDefaults());
//         return http.build();
//     }
// }