package com.ltp.gradesubmission.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ltp.gradesubmission.entity.User;
import com.ltp.gradesubmission.service.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

  @Autowired
  private UserService userServiceImpl;

  // this bean is created in the main class
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    User user = userServiceImpl.getUser(authentication.getName());
    if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
      throw new BadCredentialsException("Incorrect Password!!!");
    }

    return new UsernamePasswordAuthenticationToken(authentication.getName(), user.getPassword());
  }

}
