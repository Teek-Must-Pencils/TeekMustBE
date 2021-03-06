package com.binar.teekmustbe.config;

import com.binar.teekmustbe.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public AuthFilterConfig authenticationJwtTokenFilter() {
        return new AuthFilterConfig();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // TODO: Change endpoint
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/category/**").permitAll()
                .antMatchers("/api/offer/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/notification/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/offer/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/offer/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/wishlist/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/wishlist/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/product/**").hasAuthority(Roles.SELLER.name())
                .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/profile/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/profile/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/profile/**").authenticated()
                .antMatchers("/api/seller/**").hasAuthority(Roles.SELLER.name())
                .antMatchers("/api/buyer/**").hasAuthority(Roles.BUYER.name())
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**")
                .antMatchers("/api/public/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
