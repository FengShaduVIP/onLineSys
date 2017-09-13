package com.twp.dao;

import java.util.List;
import java.util.Map;

import com.twp.entity.StuInfoEntity;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-08-05 15:59:28
 */
public interface StuInfoDao extends BaseDao<StuInfoEntity> {
	
	int updateUserInfo(long id,String username);
	
	int deleteUser(Object id);
	
	int queryStuClass(long userId);
	
	List findStuByNo(Integer cId, Integer classid);
}
