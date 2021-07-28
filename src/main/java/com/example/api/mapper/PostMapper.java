package com.example.api.mapper;

import com.example.api.dto.PostRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PostMapper {
  PostResponseDto fromEntity(PostEntity entity);
  List<PostResponseDto> fromEntities(List<PostEntity> entities);
  @Mapping(target = "author.id", source = "authorId")
  PostEntity fromDto(PostRequestDto postRequestDto);
}
