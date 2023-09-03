package com.ltp.gradesubmission.security;

import lombok.AllArgsConstructor;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.ltp.gradesubmission.security.filter.AuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationFilter authenticationFilter = new AuthenticationFilter();
    authenticationFilter.setFilterProcessesUrl("/authenticate");
    http
        .headers().frameOptions().disable().and().csrf().disable().authorizeRequests() // New Line: the h2 console runs
                                                                                       // on a "frame". By default,
                                                                                       // Spring Security prevents
                                                                                       // rendering within an iframe.
                                                                                       // This line disables its
                                                                                       // prevention.

        .antMatchers("/h2/**").permitAll() // New Line: allows us to access the h2 console without the need to
                                           // authenticate. ' ** ' instead of ' * ' because multiple path levels will
                                           // follow /h2.
        .antMatchers(SecurityConstants.REGISTER_PATH).permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(authenticationFilter)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }
}