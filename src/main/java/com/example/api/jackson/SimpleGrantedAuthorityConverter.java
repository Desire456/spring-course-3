package com.example.api.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleGrantedAuthorityConverter extends StdConverter<List<GrantedAuthority>, List<String>> {
  @Override
  public List<String> convert(List<GrantedAuthority> value) {
    return value.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
  }
}
