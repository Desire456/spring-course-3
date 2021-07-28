package com.example.api.mapper;

import com.example.api.dto.LikeResponseDto;
import com.example.api.entity.LikeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LikeMapper {
    @Mapping(target = "username", source = "author.username")
    @Mapping(target = "postId", source = "post.id")
    LikeResponseDto fromEntity(LikeEntity likeEntity);
}
