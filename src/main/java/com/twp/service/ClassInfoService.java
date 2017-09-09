package com.twp.service;

import com.twp.entity.ClassInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-07-03 16:25:50
 */
public interface ClassInfoService {
	
	ClassInfoEntity queryObject(Integer classId);
	
	List<ClassInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassInfoEntity classInfo);
	
	void update(ClassInfoEntity classInfo);
	
	void delete(String classId);
	
	void deleteBatch(String[] classIds);
}
