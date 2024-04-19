package org.kshrd.service.implement;

import org.kshrd.exception.CustomNotFoundException;
import org.kshrd.model.dto.request.ExpenseRequest;
import org.kshrd.model.entity.Category;
import org.kshrd.model.entity.Expense;
import org.kshrd.repository.ExpenseRepository;
import org.kshrd.service.ExpenseService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getAllExpense(Integer offset, Integer limit, String currentUser) {
        return expenseRepository.getAllExpense(offset,limit,currentUser);
    }

    @Override
    public Expense getExpenseById(Integer id, String currentUser) {
        if (expenseRepository.getExpenseById(id,currentUser) == null) {
            throw new CustomNotFoundException("The Expense with id " + id + " has not been founded.");
        }
        return expenseRepository.getExpenseById(id, currentUser);
    }

    @Override
    public void deleteExpenseById(Integer id, String currentUser) {
        if (expenseRepository.getExpenseById(id,currentUser) == null) {
            throw new CustomNotFoundException("The Expense with id " + id + " has not been founded.");
        }
        expenseRepository.deleteCategory(id, currentUser);
    }


    @Override
    public Expense insertExpense(ExpenseRequest expenseRequest, String user) {
        return expenseRepository.insertExpense(expenseRequest,  user);
    }

    @Override
    public Expense updateExpense(Integer id, ExpenseRequest expenseRequest, String currentUser) {
        if (expenseRepository.getExpenseById(id,currentUser) == null) {
            throw new CustomNotFoundException("The Expense with id " + id + " has not been founded.");
        }
        return expenseRepository.updateExpense(id,expenseRequest,currentUser);
    }
}
