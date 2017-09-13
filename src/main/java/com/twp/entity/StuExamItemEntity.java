package com.twp.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 22:55:25
 */
public class StuExamItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer examPaperId;
	//
	private Integer itemId;
	//
	private Integer stuId;
	//
	private Integer score;
	//
	private Integer createTime;
	//
	private Integer classId;
	//
	private Integer examTestId;
	//
	private Integer status;

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
	public void setExamPaperId(Integer examPaperId) {
		this.examPaperId = examPaperId;
	}
	/**
	 * 获取：
	 */
	public Integer getExamPaperId() {
		return examPaperId;
	}
	/**
	 * 设置：
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：
	 */
	public Integer getItemId() {
		return itemId;
	}
	/**
	 * 设置：
	 */
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	/**
	 * 获取：
	 */
	public Integer getStuId() {
		return stuId;
	}
	/**
	 * 设置：
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
	/**
	 * 获取：
	 */
	public Integer getScore() {
		return score;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Integer getCreateTime() {
		return createTime;
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
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
}
