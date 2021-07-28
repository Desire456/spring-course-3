package com.example.api.rest;

import com.example.api.dto.RegistrationRequestDto;
import com.example.api.dto.RegistrationResponseDto;
import com.example.api.dto.UserInfoResponseDto;
import com.example.api.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @SecurityRequirements
    @PostMapping("/registrations")
    public RegistrationResponseDto register(@RequestBody @Valid RegistrationRequestDto dto) {
        return userService.register(dto);
    }

    @GetMapping("/me")
    public UserInfoResponseDto me() {
        return userService.me();
    }
}
