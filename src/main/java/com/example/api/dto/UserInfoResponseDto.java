package com.example.api.dto;

import com.example.api.jackson.SimpleGrantedAuthorityConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponseDto {
  private long id;
  private String name;
  private String username;
  private String avatar;
  @JsonSerialize(converter = SimpleGrantedAuthorityConverter.class)
  private List<GrantedAuthority> authorities;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;
  private Instant created;
}
