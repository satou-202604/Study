package com.example.point.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.point.entity.MemberEntity;

@Mapper
public interface MemberMapper {

    @Select("SELECT id, member_code, name, email, phone, point_balance, rank, " +
            "joined_at, created_at, updated_at FROM members WHERE id = #{id}")
    Optional<MemberEntity> findById(@Param("id") Integer id);

    @Update("UPDATE members SET point_balance = #{pointBalance}, updated_at = NOW() WHERE id = #{id}")
    void updatePointBalance(@Param("id") Integer id, @Param("pointBalance") Integer pointBalance);
}