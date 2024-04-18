package org.kshrd.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Positive;
import org.apache.ibatis.annotations.Param;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.dto.response.CategoryResponse;
import org.kshrd.model.entity.Category;
import org.kshrd.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController (CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping
    public ResponseEntity<?> getAllCategory(
            @Positive(message = "Offset must be not zero or negative number")
            Integer offset,
            @Positive(message = "Limit must be not zero or negative number")
            Integer limit
    ){
        List<Category> categories = categoryService.getAllCategory(offset, limit);
        CategoryResponse<List<Category>> categoryResponse = CategoryResponse.<List<Category>>builder()
                .message("Get all categories successfully.")
                .payload(categories)
                .status("100")
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        Category category = categoryService.getCategoryById(id);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Get categories by id successfully")
                .payload(category)
                .status("100")
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }
    @PostMapping
     public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){
        Category category = categoryService.createCategory(categoryRequest);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Create category successfully")
                .payload(category)
                .status("CREATE")
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest){
        categoryService.updateCategory(id,categoryRequest);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("Category has been updated")
                .payload(categoryRequest)
                .status("100")
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        CategoryResponse<?> categoryResponse = CategoryResponse.builder()
                .message("The category has been successfully removed.")
                .status("100")
                .localDateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(categoryResponse);

    }

}


