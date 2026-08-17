package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.SubjectMapper;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.form.SubjectForm;

@Service
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectEntity> searchAll() {
        return subjectMapper.findAll();
    }

    public void create(SubjectForm subjectRequest) {
        SubjectEntity subject = new SubjectEntity();
        subject.setSubject(subjectRequest.getSubject());
        subjectMapper.save(subject);
    }
   
    /**
     * 科目情報 主キー検索
     * @return  検索結果
     */
    public SubjectEntity findById(Integer id) {
        return subjectMapper.getOne(id);
    }
   
    /**
     * 科目情報 更新
     * @param  subject 科目情報
     */
    public void update(SubjectForm subjectUpdateRequest) {
        SubjectEntity subject = findById(subjectUpdateRequest.getId());
        subject.setSubject(subjectUpdateRequest.getSubject());
        subjectMapper.editUpdate(subject);
    }
    /**
	 * 科目情報 物理削除
	 * @param  id ID
	 */
	public void delete(Integer id) {
		SubjectEntity subject = findById(id);
		subjectMapper.delete(subject);
	}
}
    
    
 