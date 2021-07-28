package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationResponseDto   {
  private long id;
  private String username;
  private String name;
  private String avatar;
  private String token;
}
