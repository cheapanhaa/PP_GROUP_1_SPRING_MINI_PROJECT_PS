package org.kshrd.service;

import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.dto.request.ExpenseRequest;
import org.kshrd.model.entity.Category;
import org.kshrd.model.entity.Expense;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ExpenseService {
    List<Expense> getAllExpense(Integer offset, Integer limit, String currentUser);
    Expense getExpenseById(Integer id, String currentUser);
    void deleteExpenseById(Integer id,String currentUser);
    Expense insertExpense(ExpenseRequest expenseRequest, String user);
    Expense updateExpense(Integer id, ExpenseRequest expenseRequest, String currentUser);

}
