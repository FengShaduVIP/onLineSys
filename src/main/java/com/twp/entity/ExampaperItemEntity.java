package com.twp.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-21 14:22:45
 */
public class ExampaperItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//试卷id
	private Integer exampaperId;
	//题目id
	private Integer itemId;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：试卷id
	 */
	public void setExampaperId(Integer exampaperId) {
		this.exampaperId = exampaperId;
	}
	/**
	 * 获取：试卷id
	 */
	public Integer getExampaperId() {
		return exampaperId;
	}
	/**
	 * 设置：题目id
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：题目id
	 */
	public Integer getItemId() {
		return itemId;
	}
}
