package org.kshrd.controller;

import org.kshrd.model.ApiResponse;
import org.kshrd.model.AppUserDto;
import org.kshrd.model.AppUserRequest;
import org.kshrd.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AppUserService appUserService;

    public AuthenticationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserRequest appUserRequest) {
        AppUserDto appUserDto = appUserService.registerUser(appUserRequest);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .message("User registered successfully")
                        .status(HttpStatus.CREATED)
                        .code(201)
                        .data(appUserDto)
                        .build()
        );
    }
}
