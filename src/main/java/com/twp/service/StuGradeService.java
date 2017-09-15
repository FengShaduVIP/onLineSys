package com.twp.service;

import com.twp.entity.StuGradeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 23:02:45
 */
public interface StuGradeService {
	
	StuGradeEntity queryObject(Integer id);
	
	List<StuGradeEntity> queryList(Map<String, Object> map);
	
	List<Map<String, Object>> StuGradeList(Map<String, Object> map);
	
	List<Map<String, Object>> StuGradeLists(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int queryStuTotal(Map<String, Object> map);
	
	int queryStuTotals (Map<String, Object> map);
	
	void save(StuGradeEntity stuGrade);
	
	void update(StuGradeEntity stuGrade);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void saveStuGrade(Long userId, Integer examTestId,Integer stuSumScore);

	StuGradeEntity queryObjByMap(Long stuId,Integer examTestId);
}
