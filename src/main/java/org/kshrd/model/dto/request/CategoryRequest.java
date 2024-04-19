package org.kshrd.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequest {
    @NotBlank(message = "Category must be not blank")
    @NotNull(message = "Category must be not null")
    @NotEmpty(message = "Category must be not empty")
    private String name;

    @NotBlank(message = "Description must be not blank")
    @NotNull(message = "Description must be not null")
    @NotEmpty(message = "Description must be not empty")
    private String description;
}
