package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.dto.request.CategoryRequest;
import org.kshrd.model.entity.Category;

import java.util.List;

@Mapper
public interface CategoryRepository {

    @Select("""
            INSERT INTO categories(name, description, user_id)
            VALUES(#{categoryRequest.name}, #{categoryRequest.description}, (SELECT user_id FROM users WHERE email = #{currentUser}))
            returning *
            """)
    @Results(id = "CategoryResultMap", value = {
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "user", column = "user_id",
                    one = @One(select = "org.kshrd.repository.AppUserRepository.getUserById"))
    })
    Category createCategory(@Param("categoryRequest") CategoryRequest categoryRequest, @Param("currentUser") String currentUser);

    @Select("""
            SELECT * FROM categories
            WHERE user_id = (SELECT user_id FROM users WHERE email = #{currentUser})
            LIMIT #{limit} OFFSET #{offset}
            """)
    @ResultMap("CategoryResultMap")
    List<Category> getAllCategories(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("currentUser") String currentUser);

    @Select("""
            SELECT * FROM categories
            WHERE category_id =#{id}
            """)
    @ResultMap("CategoryResultMap")
    Category getCategoriesById(@Param("id") Integer id, @Param("currentUser") String currentUser);

    @Select("""
            UPDATE categories SET name = #{categoryRequest.name}, description = #{categoryRequest.description} WHERE category_id = #{id}
            RETURNING *
            """)
    @ResultMap("CategoryResultMap")
    Category updateCategory(Integer id, @Param("categoryRequest") CategoryRequest categoryRequest, @Param("currentUser") String currentUser);

    @Delete("""
            DELETE FROM categories WHERE category_id = #{id}
            """)
    void deleteCategory (Integer id);
}
