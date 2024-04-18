package org.kshrd.service.implement;

import org.kshrd.model.dto.response.OtpsDTO;
import org.kshrd.repository.OtpRepository;
import org.kshrd.service.OtpService;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public void saveOtp(OtpsDTO otpsDTO) {
        otpRepository.saveOtp(otpsDTO);
    }

    @Override
    public OtpsDTO getOtp(String otp) {
        return otpRepository.getOtp(otp);
    }

    @Override
    public OtpsDTO updateOtp(OtpsDTO otpsDTO) {
        return otpRepository.updateOtp(otpsDTO);
    }
}
