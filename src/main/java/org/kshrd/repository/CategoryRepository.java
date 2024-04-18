package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryRepository {
    @Select("""
            SELECT * FROM categories LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
            """)
    @Results(id = "categoryMapping", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "users", column = "category_id",
                    one = @One(select = "org.kshrd.repository.AppUserRepository.getAllUserByCategoryId")
            )
    })
    List<Category> getAllCategory(Integer offset, Integer limit);

    @Select("""
            SELECT * FROM categories WHERE category_id = #{id}
            """)
    @ResultMap("categoryMapping")
    Category getCategoryById(Integer id);

    @Select("""
                    INSERT INTO categories (name,description) values (#{name},#{description}) RETURNING *  
            """)
    @ResultMap("categoryMapping")
    Category createCategory(CategoryRequest categoryRequest);

    @Update("""
            UPDATE categories SET name= #{categoryRequest.name},description= #{categoryRequest.description}
            """)
    void updateCategory(Integer id,@Param("categoryRequest") CategoryRequest categoryRequest);

    @Delete("""
             DELETE FROM categories WHERE category_id = #{id}
             """)
    void deleteCategory(Integer id);
}
