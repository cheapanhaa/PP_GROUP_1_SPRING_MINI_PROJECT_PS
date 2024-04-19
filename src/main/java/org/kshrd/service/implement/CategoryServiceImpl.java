package org.kshrd.service.implement;

import org.kshrd.exception.CustomNotFoundException;
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
        if (categoryRepository.getCategoriesById(id,currentUser) == null) {
            throw new CustomNotFoundException("The categories with id " + id + " has not been founded.");
        }
        return categoryRepository.getCategoriesById(id, currentUser);
    }

    @Override
    public Category updateCategory(Integer id, CategoryRequest categoryRequest, String currentUser) {
        getCategoriesById(id, currentUser);
        return categoryRepository.updateCategory(id,categoryRequest,currentUser);
    }

    @Override
    public void deleteCategory(Integer id, String currentUser) {
        getCategoriesById(id,currentUser);
        categoryRepository.deleteCategory(id,currentUser);
    }

}
