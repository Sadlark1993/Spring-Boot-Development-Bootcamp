package com.ltp.gradesubmission.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.security.SecurityConstants;
import com.ltp.gradesubmission.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  CustomAuthenticationManager customAuthenticationManager;

  /*
   * on HTTP request on /authenticate, this method will be called. Default is
   * /login, but we changed that at the SecurityConfig.java
   */
  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try { // it throws an IOException. We need to catch it.
      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
      Authentication auth2 = customAuthenticationManager.authenticate(authentication);
      return auth2;
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(failed.getMessage());
    response.getWriter().flush();
    System.out.println("Authentication failed <----------------------------");
  }

  // creates the JWT token to send to the client
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    String token = JWT.create()
        // this is our payload
        .withSubject(authResult.getName())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
        // the signature
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET_KEY));

    response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
    // Authorization : Bearer + JWT token
    System.out.println("Authentication worked <-----------------------");
  }
}
