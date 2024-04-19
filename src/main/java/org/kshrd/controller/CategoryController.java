package org.kshrd.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.dto.response.CategoryResponse;
import org.kshrd.model.entity.Category;
import org.kshrd.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        String currentUser = getUsernameOfCurrentUser();
        Category category = categoryService.createCategory(categoryRequest, currentUser);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Create category successfully")
                .payload(category)
                .status(HttpStatus.CREATED)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories(@RequestParam(defaultValue = "1") Integer offset, @RequestParam(defaultValue = "5") Integer limit) {
        String currentUser = getUsernameOfCurrentUser();
        List<Category> categoryList = categoryService.getAllCategories(offset, limit, currentUser);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Get all categories successfully")
                .payload(categoryList)
                .status(HttpStatus.OK)
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoriesById(@PathVariable Integer id ){
        String currentUser = getUsernameOfCurrentUser();
        Category category = categoryService.getCategoriesById(id,currentUser);
        System.out.println(category);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Get categories by id successfully.")
                .payload(category)
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


