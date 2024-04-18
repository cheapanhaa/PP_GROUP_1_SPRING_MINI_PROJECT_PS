package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.dto.request.AppUserRequest;
import org.kshrd.model.entity.AppUser;

@Mapper
public interface AppUserRepository {

    @Select("""
             SELECT * FROM users WHERE email = #{email}
            """)
    AppUser findByEmail(@Param("email") String email);

    @Select("""
            INSERT INTO users (email, password, profile_image)
            VALUES (#{user.email} , #{user.password}, #{user.profileImage})
            RETURNING *
            """)
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "profileImage", column = "profile_image")
    })
    AppUser saveUser(@Param("user") AppUserRequest appUserRequest);

    @Select("SELECT user_id FROM users WHERE email = #{email}")
    Integer getUserIdByEmail(@Param("email") String email);
}
