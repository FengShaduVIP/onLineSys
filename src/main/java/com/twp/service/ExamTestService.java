package com.twp.service;

import com.twp.entity.ExamTestEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 15:21:26
 */
public interface ExamTestService {
	
	ExamTestEntity queryObject(Integer id);
	
	List<ExamTestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ExamTestEntity examTest);
	
	void update(ExamTestEntity examTest);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}