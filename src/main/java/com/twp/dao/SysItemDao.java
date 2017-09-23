package com.twp.dao;

import com.twp.entity.SysItemEntity;

import java.util.Map;

public interface SysItemDao extends BaseDao<SysItemEntity> {

	String findLastId();

    int queryStuTotal(Map<String, Object> map);
}
