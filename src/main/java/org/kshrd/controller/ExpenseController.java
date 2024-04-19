package org.kshrd.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.kshrd.exception.CustomNotFoundException;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.dto.request.ExpenseRequest;
import org.kshrd.model.dto.response.ApiResponse;
import org.kshrd.model.dto.response.CategoryResponse;
import org.kshrd.model.entity.Category;
import org.kshrd.model.entity.Expense;
import org.kshrd.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            @RequestParam(defaultValue = "5") @Positive(message = "Limit must be greater than 0") Integer limit,
            @RequestParam(defaultValue = "expense_id") String shortBy,
            @RequestParam(defaultValue = "false") boolean orderBY

    )
    {
        String currentUser=getUsernameOfCurrentUser();
        List<Expense> expenseList = expenseService.getAllExpense(offset, limit,shortBy,orderBY,currentUser);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Get all expense successfully")
                .payload(expenseList)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
          return ResponseEntity.ok(categoryResponse);
    }
         @GetMapping("/{id}")
        public ResponseEntity<?> getExpenseById(@PathVariable Integer id){
             String currentUser = getUsernameOfCurrentUser();
             Expense expense =  expenseService.getExpenseById(id,currentUser);
             CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                     .message("Get Expense by id successfully.")
                     .payload(expense)
                     .status(HttpStatus.OK)
                     .localDateTime(LocalDateTime.now())
                     .build();
             return ResponseEntity.ok(categoryResponse);
        }
        @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpenseById(@PathVariable Integer id) {
            String currentUser=getUsernameOfCurrentUser();
            expenseService.deleteExpenseById(id,currentUser);
            CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                    .message("Expense has been remove successfully.")
                    .payload(null)
                    .status(HttpStatus.OK)
                    .localDateTime(LocalDateTime.now())
                    .build();
            return ResponseEntity.ok(categoryResponse);
    }
    @PostMapping
    public ResponseEntity<?> insertExpense(@RequestBody ExpenseRequest expenseRequest){
        String currentUser=getUsernameOfCurrentUser();
        Expense expense = expenseService.insertExpense(expenseRequest,currentUser);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "The expense has been successfully added.",
                HttpStatus.OK,
                201,
                expense
        );
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Integer id, @RequestBody ExpenseRequest expenseRequest) {
        String currentUser = getUsernameOfCurrentUser();
        Expense expense = expenseService.updateExpense(id,expenseRequest,currentUser);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Expense has been updated successfully.")
                .payload(expense)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }

    String getUsernameOfCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        System.out.println(username);
        return username;
    }

}
