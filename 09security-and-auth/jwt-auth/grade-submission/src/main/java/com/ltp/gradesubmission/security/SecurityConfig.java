package com.ltp.gradesubmission.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.ltp.gradesubmission.security.filter.AuthenticationFilter;
import com.ltp.gradesubmission.security.filter.ExceptionHandlerFilter;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
  CustomAuthenticationManager customAuthenticationManager;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
    authenticationFilter.setFilterProcessesUrl("/authenticate");
    http
        /*
         * New Line: the h2 console runs on a "frame". By default, Spring Security
         * prevents rendering within an iframe. This line disables its prevention.
         */
        .headers().frameOptions().disable().and().csrf().disable().authorizeRequests()
        /*
         * New Line: allows us to access the h2 console without the need to
         * authenticate.
         */
        .antMatchers("/h2/**").permitAll()
        .antMatchers(SecurityConstants.REGISTER_PATH).permitAll()
        .anyRequest().authenticated()
        .and()
        // invoked before authenticationFilter
        .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
        .addFilter(authenticationFilter)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
}
