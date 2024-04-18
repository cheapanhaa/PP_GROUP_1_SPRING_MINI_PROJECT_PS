package org.kshrd.service;

import org.kshrd.model.dto.response.OtpsDTO;

public interface OtpService {

    void saveOtp(OtpsDTO otpsDTO);
    OtpsDTO getOtp(String otp);

    OtpsDTO updateOtp(OtpsDTO otpsDTO);
}
