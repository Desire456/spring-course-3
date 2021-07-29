package com.example.api.repository;

import com.example.api.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findById(Long id);

    boolean existsByAuthor_UsernameAndId(String username, Long id);
}
