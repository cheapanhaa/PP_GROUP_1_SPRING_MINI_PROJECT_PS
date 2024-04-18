package org.kshrd.controller;

import org.kshrd.model.dto.response.ApiResponse;
import org.kshrd.model.entity.Expense;
import org.kshrd.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<?> getAllExpenses(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "5") Integer limit) {
        List<Expense> expenseList = expenseService.getAllExpense(offset, limit);
        ApiResponse<List<Expense>> apiResponse = new ApiResponse<>(
                "get All expense successfully",
                HttpStatus.OK,
                200,
                expenseList);
        return ResponseEntity.ok(apiResponse);
    }
         @GetMapping("/{id}")
        public ResponseEntity<?> getExpenseById(@PathVariable Integer id){
        List<Expense> expenseList=expenseService.getExpenseById(id);
        ApiResponse<List<Expense>> apiResponse = new ApiResponse<>(
                "The expense has been successfully founded. ",
                HttpStatus.OK,
                200,
                expenseList

        );
        return ResponseEntity.ok(apiResponse);
        }
}
