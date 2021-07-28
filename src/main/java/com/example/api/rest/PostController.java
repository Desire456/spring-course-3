package com.example.api.rest;

import com.example.api.domain.UserInfo;
import com.example.api.dto.LikeResponseDto;
import com.example.api.dto.PostRequestDto;
import com.example.api.dto.PostResponseDto;
import com.example.api.service.LikeService;
import com.example.api.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;
    private LikeService likeService;

    @GetMapping
    public List<PostResponseDto> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "50") @Min(0) @Max(50) int count
    ) {
        return postService.findAll(page, count);
    }

    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public PostResponseDto save(@RequestBody PostRequestDto postRequestDto) {
        return postService.save(postRequestDto, (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @PutMapping
    @PreAuthorize("@authorizationService.isPostOwner(principal, #postRequestDto)")
    public PostResponseDto update(@RequestBody PostRequestDto postRequestDto) {
        return postService.update(postRequestDto);
    }

    @DeleteMapping
    @PreAuthorize("@authorizationService.isPostOwner(principal, #postRequestDto) or hasRole('ADMIN')")
    public PostResponseDto delete(@RequestBody PostRequestDto postRequestDto) {
        return postService.delete(postRequestDto);
    }

    @PatchMapping("/{id}/restore")
    @PreAuthorize("@authorizationService.canRestorePost(principal, #id)")
    public PostResponseDto restore(@PathVariable Long id) {
        return postService.restore(id);
    }

    @PatchMapping({"/{id}/like"})
    @PreAuthorize("hasRole('USER')")
    public LikeResponseDto like(@PathVariable Long id) {
        return likeService.save((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), id,
                1);
    }

    @PatchMapping("/{id}/dislike")
    @PreAuthorize("hasRole('USER')")
    public LikeResponseDto dislike(@PathVariable Long id) {
        return likeService.save((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), id,
                -1);
    }
}
