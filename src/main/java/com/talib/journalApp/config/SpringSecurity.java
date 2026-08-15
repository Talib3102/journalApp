package com.talib.journalApp.config;

import com.talib.journalApp.service.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//How to fix it
//Instead of extending WebSecurityConfigurerAdapter and overriding configure(HttpSecurity http), you now declare a @Bean of type SecurityFilterChain in your
//SpringSecurity.java
//class.
//@Configuration
//@EnableWebSecurity
//public class SpringSecurity{
//    @Autowired
//    private CustomUserDetailsServiceImpl customUserDetailsService;
//    // Method 1: Controls URL access rules
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/journal/**").permitAll() // Example: Allow public endpoints and Replaces antMatchers("/hello").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/user").permitAll() // signup is public
//                        .anyRequest().authenticated()             // All other requests require authentication
//                )
//                .formLogin(form -> form.permitAll())         // Enables default Form Login
//                .httpBasic(basic -> {})                     // Enables HTTP Basic Authentication (optional)
//                .csrf(csrf -> csrf.disable()); // New way to disable CSRF
//        http.csrf().disable(); this is old way to disable crf
//        return http.build();
//    }
//    //Method 2: Controls HOW users are authenticated
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        In newer Spring Security (6.x / 7.x — which your Spring Boot 4.1.0 uses), the setUserDetailsService() setter method was removed. Instead, UserDetailsService is now passed directly through the constructor:
//       // The constructor signature in your version:
//public DaoAuthenticationProvider(UserDetailsService userDetailsService)
//                                ↑ must pass it here, no setter anymore
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
//In Spring Security 6.0+ (used by Spring Boot 3.0 and newer versions), the WebSecurityConfigurerAdapter class has been completely removed.
//Why was it removed?
//Spring moved away from inheritance-based security configuration to a component-based (bean-based) configuration model to improve flexibility, reduce boilerplate code, and prevent common configuration pitfalls.

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
              .authorizeRequests()
                .antMatchers("/journal/**","/user/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
//                .formLogin();//This enable form based authentication
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());//this is use to check that the user login its password is match to the password of database user if match then enter otherwise throw forbidden
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}