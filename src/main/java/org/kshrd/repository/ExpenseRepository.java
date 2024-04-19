package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.dto.request.ExpenseRequest;
import org.kshrd.model.entity.Category;
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
            @Result(property = "userId", column = "user_id",
            one = @One(select = "org.kshrd.repository.AppUserRepository.getUserById")),
            @Result(property = "categoryId", column = "category_id",
            one = @One(select = "org.kshrd.repository.AppUserRepository.getCategoryById")
            )
    })
    List<Expense> getAllExpense(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("currentUser") String currentUser);

    @Select("""
        SELECT * FROM expenses
        WHERE expense_id = #{expenseId}
       """)
    @ResultMap("expenseMapping")
    Expense getExpenseById(@Param("expenseId") Integer id, @Param("currentUser") String currentUser);

    @Select("""
        DELETE FROM expenses
        WHERE expense_id = #{id}
        """)
    @ResultMap("expenseMapping")
    void deleteCategory (@Param("id") Integer id, @Param("currentUser")String currentUser);


    @Select("""
            INSERT INTO expenses(amount,description,date,category_id,user_id) VALUES(
            #{expense.amount},
            #{expense.amount},#{expense.date},#{expense.categoryId}, (SELECT user_id FROM users WHERE email = #{currentUser})) RETURNING *
        """)
    @ResultMap("expenseMapping")
    Expense insertExpense(@Param("expense")ExpenseRequest expenseRequest,@Param("currentUser")  String user);

    @Select("""
            UPDATE expenses SET amount = #{expense.amount}, description = #{expense.description} ,date= #{expense.date},category_id= #{expense.categoryId} WHERE expense_id = #{id}
            RETURNING *
            """)
    @ResultMap("expenseMapping")
    Expense updateExpense(Integer id, @Param("expense") ExpenseRequest expenseRequest, @Param("currentUser") String currentUser);

}


