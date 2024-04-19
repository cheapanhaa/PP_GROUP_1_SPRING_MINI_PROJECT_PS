package org.kshrd.service.implement;

import jakarta.mail.MessagingException;
import org.kshrd.model.dto.request.AppUserRequest;
import org.kshrd.model.dto.response.AppUserDTO;
import org.kshrd.model.dto.response.OtpsDTO;
import org.kshrd.model.entity.AppUser;
import org.kshrd.repository.AppUserRepository;
import org.kshrd.service.AppUserService;
import org.kshrd.service.OtpService;
import org.kshrd.util.EmailUtil;
import org.kshrd.util.OtpUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private OtpUtil otpUtil;

    @Autowired
    private EmailUtil emailUtil;

    private final AppUserRepository appUserRepository;
    private final OtpService otpService;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public AppUserServiceImpl(AppUserRepository appUserRepository, OtpService otpService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.appUserRepository = appUserRepository;
        this.otpService = otpService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public AppUserDTO registerUser(AppUserRequest appUserRequest) {
        String passwordEncode = passwordEncoder.encode(appUserRequest.getPassword());
        appUserRequest.setPassword(passwordEncode);
        AppUser appUser = appUserRepository.saveUser(appUserRequest);
        System.out.println(appUser.getUserId());

        OtpsDTO otpsDTO = otpUtil.generateOTP(appUser.getUserId());
        System.out.println(otpsDTO);

        otpService.saveOtp(otpsDTO);

        try {
            emailUtil.sendOtpEmail(appUser.getEmail(), otpsDTO.getOptCode());
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }

        return modelMapper.map(appUser, AppUserDTO.class);
    }

    @Override
    public String verifyUser(String otp) {

        OtpsDTO otpsDTO = otpService.getOtp(otp);

        if (otpsDTO.isVerify()) {
            return "OTP has already been used";
        }

        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(otpsDTO.getExpiration())) {
            return "OTP is expired";
        }

        otpsDTO.setVerify(true);
        OtpsDTO updateOtp = otpService.updateOtp(otpsDTO);
        System.out.println(updateOtp);
        return "OTP is verified";
    }

    @Override
    public String resendOtp(String email) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null) {
            return "User not found";
        }

        OtpsDTO otpsDTO = otpUtil.generateOTP(appUser.getUserId());
        otpService.saveOtp(otpsDTO);

        try {
            emailUtil.sendOtpEmail(appUser.getEmail(), otpsDTO.getOptCode());
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }

        return "OTP has been sent to your email";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email);
    }

}
