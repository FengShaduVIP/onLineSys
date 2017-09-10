package com.twp.service;

import java.util.List;
import java.util.Map;

import com.twp.entity.ExampaperItemEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-21 14:22:45
 */
public interface ExampaperItemService {
	
	ExampaperItemEntity queryObject(Integer id);
	
	List<ExampaperItemEntity> queryList(Map<String, Object> map);
	
	List<Map<String, String>> queryItemInfo(Map<String, Object> map);
	
	List<Map<String, String>> queryUnItemInfo(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ExampaperItemEntity exampaperItem);
	
	void update(ExampaperItemEntity exampaperItem);
	
	void delete(Integer id);
	
	void deleteExamItem(Integer exmaId,Integer itemId);
	
	void deleteBatch(Integer[] ids);
}
