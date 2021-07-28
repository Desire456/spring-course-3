package com.example.api.provider;

import com.example.api.service.UserTokenService;
import com.example.api.token.XAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {
  private final UserTokenService userTokenService;
  private final UserDetailsChecker userDetailsChecker;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(XAuthenticationToken.class, authentication, "Only XAuthToken is supported");

    final XAuthenticationToken token = (XAuthenticationToken) authentication;
    final String principal = (String) token.getPrincipal();
    final XAuthenticationToken result = userTokenService.findByToken(principal).map(o -> new XAuthenticationToken(
        o,
        o.getPassword(),
        o.getAuthorities()
    )).orElseThrow(() -> new BadCredentialsException("Bad token"));
    userDetailsChecker.check((UserDetails) result.getPrincipal());
    return result;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return XAuthenticationToken.class.equals(authentication);
  }
}
