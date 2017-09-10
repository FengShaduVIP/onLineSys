package com.twp.service;

import com.twp.entity.ClassTestEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 17:44:06
 */
public interface ClassTestService {
	
	ClassTestEntity queryObject(Integer id);
	
	List<ClassTestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassTestEntity classTest);
	
	void update(ClassTestEntity classTest);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
