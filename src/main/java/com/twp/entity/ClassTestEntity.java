package com.twp.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 17:44:06
 */
public class ClassTestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer classId;
	//
	private Integer examTestId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * 获取：
	 */
	public Integer getClassId() {
		return classId;
	}
	/**
	 * 设置：
	 */
	public void setExamTestId(Integer examTestId) {
		this.examTestId = examTestId;
	}
	/**
	 * 获取：
	 */
	public Integer getExamTestId() {
		return examTestId;
	}
}
