package com.twp.dao;

import java.util.List;
import java.util.Map;

import com.twp.entity.ClassInfoEntity;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-07-03 16:25:50
 */
public interface ClassInfoDao extends BaseDao<ClassInfoEntity> {
	
	List<Map<String, Object>> queryOnAdminList(Map<String, Object> map);
	
	int queryOnAdminTotal(Map<String, Object> map);
	
}
