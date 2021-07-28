package com.example.api.dto;

import com.example.api.constraint.NameConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationRequestDto   {
  @NotNull
  @Size(min = 3, max = 20)
  @NameConstraint
  private String username;
  @NotNull
  @Size(min = 8, max = 64)
  private String password;
  @NotNull
  private String name;
  private String avatar;
}
