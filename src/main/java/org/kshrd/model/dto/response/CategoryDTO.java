package org.kshrd.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CategoryDTO {
    private int categoryId;
    private String name;
    private String description;
}
