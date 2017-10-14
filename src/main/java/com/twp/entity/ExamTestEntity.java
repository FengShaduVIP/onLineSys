package com.twp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 15:21:26
 */
public class ExamTestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer examPaperId;
	//
	private String authorId;
	//
	private Date startTime;
	//
	private Date endTime;
	//
	private Integer createTime;
	//
	private Integer status;
	//
	private String examTitle;
	//
	private Integer classId;
	//
	private List<String> classIds;

	private  Integer hour;

	private  Integer minute;


	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	/**
	 * 设置：
	 */
	public void setClassIds(List<String> classIds) {
		this.classIds = classIds;
	}
	/**
	 * 获取：
	 */
	public List<String> getClassIds() {
		return classIds;
	}
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
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getAuthorId() {
		return authorId;
	}
	/**
	 * 获取：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
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
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取:
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 * @param status
	 */
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
	/**
	 * 获取:
	 * @return
	 */
	public String getExamTitle() {
		return examTitle;
	}
	/**
	 * 设置：
	 * @param status
	 */
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	/**
	 * 获取:
	 * @return
	 */
	public Integer getClassId() {
		return classId;
	}
}
