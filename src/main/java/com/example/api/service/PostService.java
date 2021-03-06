package com.example.api.service;

import com.example.api.dto.PostRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import com.example.api.exception.AttributeIsNotPresentedException;
import com.example.api.exception.ItemNotFoundException;
import com.example.api.mapper.PostMapper;
import com.example.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper mapper;
    private final UserService userService;
    private final Sort idAscSort = Sort.by(Sort.Direction.ASC, "id");

    public List<PostResponseDto> findAll(int page, int count) {
        return mapper.fromEntities(
                postRepository.findAll(PageRequest.of(page, count, idAscSort)).getContent()
        );
    }

    public PostResponseDto findById(Long id) {
        return mapper.fromEntity(
                postRepository.findById(id).orElseThrow(ItemNotFoundException::new)
        );
    }

    @Transactional
    public PostResponseDto save(PostRequestDto postRequestDto, UserDetails principal) {
        UserEntity userEntity = userService.findByUsername(principal.getUsername());
        PostEntity postEntity = mapper.fromDto(postRequestDto);
        postEntity.setAuthor(userEntity);

        return mapper.fromEntity(postRepository.save(postEntity));
    }

    @Transactional
    public PostResponseDto update(PostRequestDto postRequestDto) {
        if (postRequestDto.getId() == null) {
            throw new AttributeIsNotPresentedException("id");
        }

        PostEntity post = mapper.fromDto(postRequestDto);
        post.setUpdated(Instant.now());
        return mapper.fromEntity(post);
    }

    @Transactional
    public PostResponseDto delete(PostRequestDto postRequestDto) {
        if (postRequestDto.getId() == null) {
            throw new AttributeIsNotPresentedException("id");
        }

        PostEntity post = postRepository.findById(mapper.fromDto(postRequestDto).getId())
                .orElseThrow(ItemNotFoundException::new);
        post.setDeleted(Instant.now());
        post.setDeletedBy(userService.getCurrentUserEntity());
        return mapper.fromEntity(post);
    }

    @Transactional
    public PostResponseDto restore(Long id) {
        if (id == null) {
            throw new AttributeIsNotPresentedException("id");
        }
        PostEntity post = postRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        post.setDeleted(null);
        post.setDeletedBy(null);
        return mapper.fromEntity(post);
    }

    public boolean existByAuthorUsernameAndId(String authorUsername, Long id) {
        return postRepository.existsByAuthor_UsernameAndId(authorUsername, id);
    }

}
