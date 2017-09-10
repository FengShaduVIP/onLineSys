package com.twp.service;

import com.twp.entity.StuExamItemEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 22:55:25
 */
public interface StuExamItemService {
	
	StuExamItemEntity queryObject(Integer id);
	
	List<StuExamItemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StuExamItemEntity stuExamItem);
	
	void update(StuExamItemEntity stuExamItem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
