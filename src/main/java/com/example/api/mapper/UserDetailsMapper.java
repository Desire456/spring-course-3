package com.example.api.mapper;

import com.example.api.domain.UserInfo;
import com.example.api.dto.UserInfoResponseDto;
import com.example.api.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserDetailsMapper {
  UserInfo fromEntity(UserEntity entity);
  UserInfoResponseDto fromDomain(UserInfo info);
}
