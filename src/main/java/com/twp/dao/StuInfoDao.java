package com.twp.dao;

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
	
}
