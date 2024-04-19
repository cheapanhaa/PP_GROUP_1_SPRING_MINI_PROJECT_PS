package org.kshrd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.model.dto.response.AppUserDTO;
import org.kshrd.model.dto.response.CategoryDTO;
import org.kshrd.model.dto.response.CategoryResponse;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {
    private  int expenseId;
    private int amount;
    private String Description;
    private Date date;
    private AppUserDTO userId;
    private CategoryDTO categoryId;
}
