package com.example.api.repository;

import com.example.api.entity.LikeEntity;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByAuthorAndPost(UserEntity author, PostEntity post);
}
