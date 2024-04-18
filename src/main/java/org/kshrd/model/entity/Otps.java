package org.kshrd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otps {
    private Integer optId;
    private String optCode;
    private LocalDateTime issuedDate;
    private LocalDateTime expiration;
    private String verify;
    private Integer userId;
}
