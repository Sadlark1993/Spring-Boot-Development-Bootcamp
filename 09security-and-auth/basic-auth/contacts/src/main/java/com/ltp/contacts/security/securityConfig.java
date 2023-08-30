package com.ltp.contacts.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class securityConfig {

  //lombok will generate the constructor, that will make the spring @Autowired.
  private BCryptPasswordEncoder passwordEncoder;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //this is the security rules :)
    http
      .csrf()
      .disable() //disable csrf protection that is to browser systems
      .authorizeRequests()
      .antMatchers(HttpMethod.DELETE) //to differ from methods from the same nature: steps.txt
      .hasRole("ADMIN") //only admins can delete
      .antMatchers(HttpMethod.POST)
      .hasAnyRole("USER", "ADMIN") //users and admins can do POST requests
      .antMatchers(HttpMethod.GET)
      .permitAll() //anybody can access GET requests
      .anyRequest()
      .authenticated() //any other request require auth
      .and()
      .httpBasic()
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }

  //basic auth depends on this information to authenticate users
  @Bean
  public UserDetailsService users() {
    //ignore this hard-coded password. Forward, we will implement a sign up process with JWT.
    UserDetails admin = User
      .builder()
      .username("admin")
      .password(passwordEncoder.encode("admin-pass"))
      .roles("ADMIN")
      .build();

    UserDetails user = User
      .builder()
      .username("user")
      .password(passwordEncoder.encode("user-pass"))
      .roles("USER")
      .build();

    return new InMemoryUserDetailsManager(admin, user);
  }
}
