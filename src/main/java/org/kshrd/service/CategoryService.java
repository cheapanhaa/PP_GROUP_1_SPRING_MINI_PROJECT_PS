package org.kshrd.service;


import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryRequest categoryRequest, String currentUser);

    List<Category> getAllCategories(Integer offset, Integer limit, String currentUser);

    Category getCategoriesById(Integer id,String currentUser);

    Category updateCategory(Integer id, CategoryRequest categoryRequest, String currentUser);

    void deleteCategory(Integer id,String currentUser);
}
