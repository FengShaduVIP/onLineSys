package com.twp.service;

import com.twp.entity.StuInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-08-05 15:59:28
 */
public interface StuInfoService {
	
	StuInfoEntity queryObject(Long id);
	
	List<StuInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StuInfoEntity stuInfo);
	
	void update(StuInfoEntity stuInfo);
	
	void updateUserInfo(long id,String username);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	void deleteUser(Long id);
}
