package com.example.api.service;

import com.example.api.dto.LikeResponseDto;
import com.example.api.entity.LikeEntity;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import com.example.api.exception.ItemNotFoundException;
import com.example.api.mapper.LikeMapper;
import com.example.api.repository.LikeRepository;
import com.example.api.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeMapper likeMapper;

    @Transactional
    public LikeResponseDto save(UserDetails principal, Long postId, Integer like) {
        UserEntity author = userService.findByUsername(principal.getUsername());
        PostEntity post = postRepository.findById(postId).orElseThrow(ItemNotFoundException::new);

        LikeEntity likeEntity = likeRepository.findByAuthorAndPost(author, post).orElse(null);
        if (likeEntity != null && likeEntity.getLike().equals(like)) {
            likeEntity.setLike(like);
        } else if (likeEntity != null) {
            return likeMapper.fromEntity(likeEntity);
        } else {
            likeEntity = new LikeEntity();
            likeEntity.setLike(like);
            likeEntity.setAuthor(author);
            likeEntity.setPost(post);
        }

        return likeMapper.fromEntity(
                likeRepository.save(likeEntity)
        );
    }
}
