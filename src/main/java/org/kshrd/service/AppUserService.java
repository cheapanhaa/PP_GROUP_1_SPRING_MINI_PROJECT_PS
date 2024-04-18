package org.kshrd.service;

import org.kshrd.model.dto.request.AppUserRequest;
import org.kshrd.model.dto.response.AppUserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserDTO registerUser(AppUserRequest appUserRequest);
    String verifyUser(String otp);
}
