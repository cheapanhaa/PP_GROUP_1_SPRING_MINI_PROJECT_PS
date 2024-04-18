package org.kshrd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.model.dto.response.AppUserDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    private String categoryId;
    private String name;
    private String description;
    private AppUserDTO users;
}