package org.kshrd.service.implement;

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
    public List<Expense> getAllExpense(Integer offset, Integer limit) {
        return expenseRepository.getAllExpense(offset,limit);
    }

    @Override
    public List<Expense> getExpenseById(Integer id) {
        return expenseRepository.getExpenseById(id);
    }

    @Override
    public Expense deleteExpenseById(Integer id) {
        return expenseRepository.deleteExpenseById(id);
    }
}
