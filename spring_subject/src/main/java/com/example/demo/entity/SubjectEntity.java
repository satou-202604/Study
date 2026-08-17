package com.example.demo.entity;

import lombok.Data;

/**
* ユーザー情報 Entity
*/
@Data
public class SubjectEntity  {

	 private Integer id;
	    private String subject;

	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getSubject() {
	        return subject;
	    }

	    public void setSubject(String subject) {
	        this.subject = subject;
	    }
	}