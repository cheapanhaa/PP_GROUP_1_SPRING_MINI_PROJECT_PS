package org.kshrd.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {
    private int amount;
    private String Description;
    private Date date;
    private int userId;
    private int categoryId;
}
