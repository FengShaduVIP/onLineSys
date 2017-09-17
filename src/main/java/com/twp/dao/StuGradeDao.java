package com.twp.dao;

import java.util.List;
import java.util.Map;

import com.twp.entity.StuGradeEntity;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 23:02:45
 */
public interface StuGradeDao extends BaseDao<StuGradeEntity> {
	
	List<Map<String, Object>> StuGradeList(Map<String, Object> map);
	
	List<Map<String, Object>> StuGradeLists(Map<String, Object> map);
	
	int queryStuTotal(Map<String, Object> map);
	
	int queryStuTotals(Map<String, Object> map);

	StuGradeEntity queryObjByMap(Map<String,String> map);

    int StuGradeListsCount(Map<String, Object> map);
}
