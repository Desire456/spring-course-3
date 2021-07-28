package com.example.api.repository;

import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findById(Long id);
    boolean existsByAuthorAndId(UserEntity author, Long id);
}
