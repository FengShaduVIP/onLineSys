package com.twp.service;

import java.util.List;
import java.util.Map;

import com.twp.entity.SysItemEntity;


public interface SysItemService {
	
	SysItemEntity queryObject(Integer id);
	
	List<SysItemEntity> queryList(Map<String, Object> map);
	
	List<SysItemEntity> queryStuList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysItemEntity sysItem);
	
	void update(SysItemEntity sysItem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void changeItems(Integer[] ids);
	
	String findLastId();

	public Map<String,String> bianYi(String text, String itemName, Integer itemId);

	public String judge_run(String filepath);

	int queryStuTotal(Map<String, Object> map);
}
