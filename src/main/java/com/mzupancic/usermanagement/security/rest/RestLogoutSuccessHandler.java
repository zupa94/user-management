package com.mzupancic.usermanagement.security.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RestLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(final HttpServletRequest httpServletRequest,
                              final HttpServletResponse httpServletResponse,
                              final Authentication authentication) {
    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
  }
}
