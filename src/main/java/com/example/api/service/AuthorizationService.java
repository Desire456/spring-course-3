package com.example.api.service;

import com.example.api.dto.PostRequestDto;
import com.example.api.entity.PostEntity;
import com.example.api.entity.UserEntity;
import com.example.api.exception.ItemNotFoundException;
import com.example.api.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AuthorizationService {
    private UserService userService;
    private PostService postService;
    private PostRepository postRepository;

    public boolean isPostOwner(UserDetails principal, PostRequestDto postRequestDto) {
        UserEntity author = userService.findByUsername(principal.getUsername());
        return postService.existByAuthorAndId(author, postRequestDto.getId());
    }

    public boolean canRestorePost(UserDetails principal, Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(ItemNotFoundException::new);
        UserEntity deletedBy = postEntity.getDeletedBy();
        if (deletedBy == null) {
            return false;
        } else {
            boolean deletedByAdmin = hasAdminRole(deletedBy.getAuthorities());
            boolean isActorAdmin = hasAdminRole(principal.getAuthorities());
            boolean isPostOwner = postService.existByAuthorAndId(userService.findByUsername(principal.getUsername()),
                    postId);
            if (isActorAdmin) {
                return true;
            }
            if (deletedByAdmin) {
                return false;
            }
            return isPostOwner;
        }
    }

    private boolean hasAdminRole(Collection<? extends GrantedAuthority> authorities) {
        return authorities.contains(new SimpleGrantedAuthority("ADMIN"));
    }
}
