package org.kshrd.service.implement;

import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;
import org.kshrd.repository.CategoryRepository;
import org.kshrd.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryServiceImpl (CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<Category> getAllCategory(Integer offset, Integer limit) {
        return categoryRepository.getAllCategory(offset,limit);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
       return categoryRepository.createCategory(categoryRequest);
    }

    @Override
    public void updateCategory(Integer id, CategoryRequest categoryRequest) {
         categoryRepository.updateCategory(id,categoryRequest);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }
}
