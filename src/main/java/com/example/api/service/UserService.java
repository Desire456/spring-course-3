package com.example.api.service;

import com.example.api.domain.UserInfo;
import com.example.api.dto.RegistrationRequestDto;
import com.example.api.dto.RegistrationResponseDto;
import com.example.api.dto.UserInfoResponseDto;
import com.example.api.entity.TokenEntity;
import com.example.api.entity.UserEntity;
import com.example.api.generator.TokenGenerator;
import com.example.api.mapper.RegistrationMapper;
import com.example.api.mapper.UserDetailsMapper;
import com.example.api.repository.TokenRepository;
import com.example.api.repository.UserRepository;
import com.example.api.role.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, UserTokenService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final RegistrationMapper registrationMapper;
    private final UserDetailsMapper userDetailsMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(userDetailsMapper::fromEntity)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<UserInfo> findByToken(String token) {
        return tokenRepository.findById(token)
                .map(TokenEntity::getUser)
                .map(userDetailsMapper::fromEntity);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public RegistrationResponseDto register(RegistrationRequestDto dto) {
        final String password = passwordEncoder.encode(dto.getPassword());
        final String token = tokenGenerator.generateToken();
        final UserEntity saved = userRepository.save(registrationMapper.fromDto(
                dto,
                password,
                Collections.singletonList(new SimpleGrantedAuthority(Roles.USER)),
                true,
                true,
                true,
                true
        ));
        tokenRepository.save(TokenEntity.builder().token(token).user(saved).build());
        return registrationMapper.fromEntity(saved, token);
    }

    public UserEntity getCurrentUserEntity() {
        UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByUsername(userInfo.getUsername());
    }

    public UserInfoResponseDto me() {
        return userDetailsMapper.fromDomain((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

