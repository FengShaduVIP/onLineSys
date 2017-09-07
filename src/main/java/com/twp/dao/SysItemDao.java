package com.twp.dao;

import com.twp.entity.SysItemEntity;

public interface SysItemDao extends BaseDao<SysItemEntity> {

	String findLastId();
	
}
