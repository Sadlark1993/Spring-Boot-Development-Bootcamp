
package com.ltp.gradesubmission.security.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ltp.gradesubmission.security.SecurityConstants;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

  // Authorization: Bearer + JWT token
  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String bearerToken = request.getHeader("Authorization"); // Bearer JWT

    // if the client is registering a new user:
    if (bearerToken == null || !bearerToken.startsWith(SecurityConstants.BEARER)) {
      filterChain.doFilter(request, response);
      return; // to skip the rest of the code of this method
    }

    // we want to get rid of the bearer and just keep the JWT token. So...
    String token = bearerToken.replace(SecurityConstants.BEARER, ""); // JWT
    // We need to make sure that the token the sent to us is valid

    // specify the algorithm that we wanna use to specify the JWT token.
    // must be the same that we used to produce the JWT.
    String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_KEY))
        .build() // returns a verification builder
        .verify(token) // verifies the token.
        .getSubject(); // if no exception has been thrown until now, its ok.

    /*
     * the object must have this 3 arguments. If only 2 are provided, the
     * authentication will fail.
     */
    Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    filterChain.doFilter(request, response);

  }

}