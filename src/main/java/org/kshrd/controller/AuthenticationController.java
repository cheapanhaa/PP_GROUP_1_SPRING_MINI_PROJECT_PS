package org.kshrd.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.kshrd.model.dto.request.AppUserRequest;
import org.kshrd.model.dto.request.AuthRequest;
import org.kshrd.model.dto.response.ApiResponse;
import org.kshrd.model.dto.response.AppUserDTO;
import org.kshrd.model.dto.response.AuthResponse;
import org.kshrd.security.JwtService;
import org.kshrd.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auths")
@AllArgsConstructor
public class AuthenticationController {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUserRequest appUserRequest) {
        AppUserDTO appUserDto = appUserService.registerUser(appUserRequest);
        return ResponseEntity.ok(appUserDto);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = appUserService.loadUserByUsername(username);
            if (userApp == null) {
                throw new BadRequestException("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new BadRequestException("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String otp) {
        String verifiedUser = appUserService.verifyUser(otp);
        return ResponseEntity.ok(verifiedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendOtp(@RequestParam String email) {
        String resendOtp = appUserService.resendOtp(email);
        System.out.println(resendOtp);
        return ResponseEntity.ok("OTP has been sent to your email");
    }
}
