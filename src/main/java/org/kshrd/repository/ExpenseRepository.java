package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.entity.Expense;
import java.util.List;

@Mapper
public interface ExpenseRepository {
    @Select("""
            SELECT * FROM expenses LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
            """)
    @Results(id = "expenseMapping", value = {
            @Result(property = "expenseId", column = "expense_id"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "description", column = "description"),
            @Result(property = "date", column = "date"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "categoryId", column = "category_id")
    })
    List<Expense> getAllExpense(@Param("offset") Integer offset, @Param("limit") Integer limit);
    @Select("""
        SELECT * FROM expenses
        WHERE expense_id = #{expenseId}
       """)
    @ResultMap("expenseMapping")
    List<Expense> getExpenseById(Integer expenseId);
}


