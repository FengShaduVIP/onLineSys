package com.twp.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-07-03 16:25:50
 */
public class ClassInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer classId;
	//班级id
	private String classNo;
	//班级名称
	private String className;
	//创建人id
	private Long teachId;
	//创建年份班级届
	private Integer createYear;
	//排序值
	private Integer classSort;
	//是否禁用
	private Integer status;

	private Date createTime;

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
	 * 设置：班级id
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	/**
	 * 获取：班级id
	 */
	public String getClassNo() {
		return classNo;
	}
	/**
	 * 设置：班级名称
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * 获取：班级名称
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * 设置：创建人id
	 */
	public void setTeachId(Long teachId) {
		this.teachId = teachId;
	}
	/**
	 * 获取：创建人id
	 */
	public Long getTeachId() {
		return teachId;
	}
	/**
	 * 设置：创建年份班级届
	 */
	public void setCreateYear(Integer createYear) {
		this.createYear = createYear;
	}
	/**
	 * 获取：创建年份班级届
	 */
	public Integer getCreateYear() {
		return createYear;
	}
	/**
	 * 设置：排序值
	 */
	public void setClassSort(Integer classSort) {
		this.classSort = classSort;
	}
	/**
	 * 获取：排序值
	 */
	public Integer getClassSort() {
		return classSort;
	}
	/**
	 * 设置：是否禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：是否禁用
	 */
	public Integer getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
