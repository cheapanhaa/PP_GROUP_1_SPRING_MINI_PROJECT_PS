package org.kshrd.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseRequest {
    private int amount;
    private String description;
    private Date date;
    private int categoryId;
}
