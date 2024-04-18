package org.kshrd.util;

import org.kshrd.model.dto.response.OtpsDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Component
public class OtpUtil {

    public OtpsDTO generateOTP(Integer userId) {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String output = Integer.toString(randomNumber);

        while (output.length() < 6) {
            output = "0" + output;
        }

        LocalDateTime issuedDate = LocalDateTime.now();
        LocalDateTime expiration = issuedDate.plusMinutes(1);
        return new OtpsDTO(output, issuedDate, expiration, false, userId);
    }
}
