package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ltp.gradesubmission.exception.EntityNotFoundException;

/* 
 * This will bew the first filter to be called when an /authenticate request is called. After that, an chain.doFilter() 
 * will be called to proceed with the process of authentication.
 */

public class ExceptionHandlerFilter extends OncePerRequestFilter {

  // Authorization: Bearer JWT
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Wraps the whole authentication request handler
    try {
      // All it's gonna do is to trigger the next filter in the filter chain
      filterChain.doFilter(request, response);

    } catch (EntityNotFoundException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("User not found");
      response.getWriter().flush();
    } catch (JWTVerificationException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write("Not valid authentication");
      response.getWriter().flush();
    } catch (RuntimeException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("BAD REQUEST");
      response.getWriter().flush();
    }
  }
}
