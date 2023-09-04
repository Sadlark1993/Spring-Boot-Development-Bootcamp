package com.ltp.gradesubmission.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/* 
 * This will bew the first filter to be called when an /authenticate request is called. After that, an chain.doFilter() 
 * will be called to proceed with the process of authentication.
 */

public class ExceptionHandlerFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Wraps the whole authentication request handler
    try {
      filterChain.doFilter(request, response);
    } catch (RuntimeException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      /*
       * If you want to write a String into the response:
       * 
       * response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
       * response.getWriter().write("BAD REQUEST"); // <--
       * response.getWriter().flush();
       */
    }
  }
}
