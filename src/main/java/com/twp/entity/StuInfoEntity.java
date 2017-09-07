package com.twp.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-08-05 15:59:28
 */
public class StuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//姓名
	private Long userId;
	//学号
	private Integer stuNo;
	//班级
	private Integer classId;
	//教师姓名
	private Long teachId;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：姓名
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：姓名
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：学号
	 */
	public void setStuNo(Integer stuNo) {
		this.stuNo = stuNo;
	}
	/**
	 * 获取：学号
	 */
	public Integer getStuNo() {
		return stuNo;
	}
	/**
	 * 设置：班级
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * 获取：班级
	 */
	public Integer getClassId() {
		return classId;
	}
	/**
	 * 设置：教师姓名
	 */
	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}
	/**
	 * 获取：教师姓名
	 */
	public Long getTeachId() {
		return teachId;
	}
}
