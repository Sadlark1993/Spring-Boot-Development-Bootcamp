package com.ltp.gradesubmission.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.gradesubmission.entity.User;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
      System.out.println(user.getUsername());
      System.out.println(user.getPassword());
    } catch (IOException e) {
      throw new RuntimeException();
    }
    return super.attemptAuthentication(request, response);
  }
}
