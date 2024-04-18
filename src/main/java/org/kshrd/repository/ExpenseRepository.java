package org.kshrd.repository;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.kshrd.model.entity.Expense;

import java.util.List;

public interface ExpenseRepository {
//    @Select("""
//            SELECT * FROM expenses LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
//            """)
//    @Results(id = "expenseMapping" ,value = {
//            @Result(property = "amount" ,column = "amount"),
//            @Result(property = "description",column = "description")
//    })

}
//List<Expense> getAllExpense(@Param("offset") Integer offset, @Param("limit") Integer limit)