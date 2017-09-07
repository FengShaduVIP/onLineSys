package com.twp.entity;

import java.io.Serializable;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-06 11:44:40
 */
public class SysItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer itemNum;
	//
	private String title;
	//
	private String name;
	//
	private String context;
	//
	private Integer score;
	//
	private String level;
	//
	private String author;
	//
	private String createTime;
	//
	private String isVisible;
	
	private String testBeach;

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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：
	 */
	public String getContext() {
		return context;
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
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置：
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	/**
	 * 获取：
	 */
	public String getIsVisible() {
		return isVisible;
	}
	public Integer getItemNum() {
		return itemNum;
	}
	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}
	public String getTestBeach() {
		return testBeach;
	}
	public void setTestBeach(String testBeach) {
		this.testBeach = testBeach;
	}
	
}
