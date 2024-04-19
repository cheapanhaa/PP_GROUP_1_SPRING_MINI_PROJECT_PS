package org.kshrd.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.kshrd.model.dto.response.ApiResponse;
import org.kshrd.model.entity.Expense;
import org.kshrd.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
@SecurityRequirement(name = "bearerAuth")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<?> getAllExpenses(
            @RequestParam(defaultValue = "1") @Positive(message = "Offset must be greater than 0") Integer offset,
            @RequestParam(defaultValue = "3") @Positive(message = "Limit must be greater than 0") Integer limit
    ) {
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
        @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable Integer id) {
        Expense expense =expenseService.deleteExpenseById(id);
//        ApiResponse<Expense> apiResponse = new ApiResponse<>(
//                "The expense has been successfully removed",
//                HttpStatus.OK,
//                LocalDateTime.now()
//        );
        return ResponseEntity.ok( "DeleteSuccessfully");

    }
}
