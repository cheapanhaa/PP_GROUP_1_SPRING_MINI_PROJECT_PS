package org.kshrd.service;


import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory(Integer offset, Integer limit);

    Category getCategoryById(Integer id);

    Category createCategory(CategoryRequest categoryRequest);

    void updateCategory(Integer id, CategoryRequest categoryRequest);

    void deleteCategory(Integer id);
}
