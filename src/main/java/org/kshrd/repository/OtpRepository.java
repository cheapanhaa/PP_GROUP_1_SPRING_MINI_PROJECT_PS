package org.kshrd.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.model.dto.response.OtpsDTO;

@Mapper
public interface OtpRepository {

    @Insert("""
                INSERT INTO otps(opt_code, issued_at, expiration, verify, user_id)
                VALUES(#{otpsDTO.optCode}, #{otpsDTO.issuedDate}, #{otpsDTO.expiration}, #{otpsDTO.verify}, #{otpsDTO.userId})
            """)
    @Results(id = "otpMap", value = {
            @Result(property = "optCode", column = "opt_code"),
            @Result(property = "issuedDate", column = "issued_at"),
            @Result(property = "expiration", column = "expiration"),
            @Result(property = "verify", column = "verify"),
            @Result(property = "userId", column = "user_id")
    })
    void saveOtp(@Param("otpsDTO") OtpsDTO otpsDTO);

    @Select("""
                SELECT *
                FROM otps
                WHERE opt_code = #{otp}
            """)
    @Results(id = "otpMap2", value = {
            @Result(property = "optCode", column = "opt_code"),
            @Result(property = "issuedDate", column = "issued_at"),
            @Result(property = "expiration", column = "expiration"),
            @Result(property = "verify", column = "verify"),
            @Result(property = "userId", column = "user_id")
    })
    OtpsDTO getOtp(@Param("otp") String otp);

    @Select("""
                UPDATE otps
                SET verify = #{otpsDTO.verify}
                WHERE opt_code = #{otpsDTO.optCode}
                RETURNING *
            """)
    @ResultMap("otpMap2")
    OtpsDTO updateOtp(@Param("otpsDTO") OtpsDTO otpsDTO);
}
