package org.kshrd.service;

import org.kshrd.model.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExpenseService {
    List<Expense> getAllExpense(Integer offset, Integer limit);
    List<Expense> getExpenseById(Integer id);
    Expense deleteExpenseById(Integer id);
}
