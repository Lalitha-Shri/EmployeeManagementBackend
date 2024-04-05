package com.Employee.user.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.Employee.user.security.JwtTokenFilter;

@Configuration
@AllArgsConstructor
//Spring security config class
public class SpringSecurityConfig {
    JwtTokenFilter jwtTokenFilter;
    @Bean
    public  static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->{

                               authorize .requestMatchers(HttpMethod.GET,"/api/todos").hasAnyRole("USER","ADMIN");
                               authorize .requestMatchers(HttpMethod.POST,"/api/todos").hasRole("ADMIN");
                    authorize .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll();

                                authorize .anyRequest().authenticated();
    }).sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN","USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails,admin);
//    }
//        @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return  authenticationConfiguration.getAuthenticationManager();
//
//        }


}


