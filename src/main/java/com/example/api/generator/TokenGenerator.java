package com.example.api.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.util.Base64Utils;

import java.security.SecureRandom;
import java.util.Random;

@RequiredArgsConstructor
public class TokenGenerator {
  private final Random random;
  private final int keyLength;

  public TokenGenerator(int keyLength) {
    this.random = new SecureRandom();
    this.keyLength = keyLength;
  }

  public String generateToken() {
    final byte[] bytes = new byte[keyLength];
    random.nextBytes(bytes);
    return Base64Utils.encodeToUrlSafeString(bytes);
  }
}
