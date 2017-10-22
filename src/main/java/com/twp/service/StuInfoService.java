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
	
	List<Map<String, String>> queryList2(Map<String, Object> map);
	
	int queryStuClass(long userId);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StuInfoEntity stuInfo);
	
	void update(StuInfoEntity stuInfo);
	
	void updateUserInfo(long id,String username);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	
	void deleteUser(Long id);

	List findStuByNo(Integer cId, Integer classid);

	void deleteBatchByClassId(Object[] ids);

	List<StuInfoEntity> queryListByClassIds(Object[] ids);

	void deleteByStuNo(Long id,Integer classNo);

}
