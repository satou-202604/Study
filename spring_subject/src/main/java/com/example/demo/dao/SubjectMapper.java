package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.SubjectEntity;

/**
* 科目情報 Mapper
*/
@Mapper
public interface SubjectMapper {

    /**
     * 科目情報 全検索
     * @return
     */
    List<SubjectEntity> findAll();
    
    /**
     * 科目情報 新規登録
     * @param subject 科目情報
     */
    void save(SubjectEntity subject);
    SubjectEntity getOne(Integer id);
    void editUpdate(SubjectEntity subject);
    void delete(SubjectEntity subject);
    
}

    