package com.example.api.service;

import com.example.api.domain.UserInfo;

import java.util.Optional;

public interface UserTokenService {
  Optional<UserInfo> findByToken(String token);
}
