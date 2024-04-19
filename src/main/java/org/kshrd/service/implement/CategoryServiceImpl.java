package org.kshrd.service.implement;

import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;
import org.kshrd.repository.CategoryRepository;
import org.kshrd.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest, String currentUser) {
        return categoryRepository.createCategory(categoryRequest, currentUser);
    }

    @Override
    public List<Category> getAllCategories(Integer offset, Integer limit, String currentUser) {
        return categoryRepository.getAllCategories(offset, limit, currentUser);
    }

    @Override
    public Category getCategoriesById(Integer id,String currentUser) {
        return categoryRepository.getCategoriesById(id, currentUser);
    }
}
