package com.twp.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-20 21:26:57
 */
public class ExamPaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//试卷标题
	private String title;
	//试卷要求
	private String detail;
	//创建时间
	private String createTime;
	//作者id
	private String authorId;

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
	 * 设置：试卷标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：试卷标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：试卷要求
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：试卷要求
	 */
	public String getDetail() {
		return detail;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：作者id
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	/**
	 * 获取：作者id
	 */
	public String getAuthorId() {
		return authorId;
	}
}
