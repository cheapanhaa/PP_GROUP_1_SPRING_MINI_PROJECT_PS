package org.kshrd.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpsDTO {
    private String optCode;
    private LocalDateTime issuedDate;
    private LocalDateTime expiration;
    private boolean verify;
    private Integer userId;
}
