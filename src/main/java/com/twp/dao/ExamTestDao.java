package com.twp.dao;

import java.util.List;
import java.util.Map;

import com.twp.entity.ExamTestEntity;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 15:21:26
 */
public interface ExamTestDao extends BaseDao<ExamTestEntity> {
	
	List<ExamTestEntity> queryIsGoingList(Map<String, Object> map);
	
	int queryIsGoingTotal(Map<String, Object> map);
}
