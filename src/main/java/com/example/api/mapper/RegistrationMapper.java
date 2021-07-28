package com.example.api.mapper;

import com.example.api.dto.RegistrationRequestDto;
import com.example.api.dto.RegistrationResponseDto;
import com.example.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Mapper
public interface RegistrationMapper {
  @Mapping(
      target = "avatar",
      nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  @Mapping(target = "password", source = "password")
  UserEntity fromDto(
      RegistrationRequestDto dto,
      String password,
      List<GrantedAuthority> authorities,
      boolean accountNonExpired,
      boolean accountNonLocked,
      boolean credentialsNonExpired,
      boolean enabled
  );

  RegistrationResponseDto fromEntity(UserEntity entity, String token);

}
