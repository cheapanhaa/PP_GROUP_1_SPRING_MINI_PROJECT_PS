package org.kshrd.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CategoryResponse <T> {
    private String message;
    private T payload;
    HttpStatus status;
    private LocalDateTime localDateTime;
}
