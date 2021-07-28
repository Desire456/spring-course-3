package com.example.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserInfo implements UserDetails, CredentialsContainer {
  private final long id;
  private final String name;
  private final String username;
  private String password;
  private final String avatar;
  private final List<GrantedAuthority> authorities;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final Instant created;

  @Override
  public void eraseCredentials() {
    this.password = null;
  }
}
