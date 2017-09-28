package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.twp.dao.StuInfoDao;
import com.twp.entity.StuInfoEntity;
import com.twp.service.StuInfoService;



@Service("stuInfoService")
public class StuInfoServiceImpl implements StuInfoService {
	@Autowired
	private StuInfoDao stuInfoDao;
	
	@Override
	public StuInfoEntity queryObject(Long id){
		return stuInfoDao.queryObject(id);
	}


	@Override
	public List<Map<String, String>> queryList2(Map<String, Object> map){
		return stuInfoDao.queryList2(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuInfoDao.queryTotal(map);
	}
	
	@Override
	public int queryStuClass(long userId){
		return stuInfoDao.queryStuClass(userId);
	}
	
	@Override
	public void save(StuInfoEntity stuInfo){
		stuInfoDao.save(stuInfo);
	}
	
	@Override
	public void update(StuInfoEntity stuInfo){
		stuInfoDao.update(stuInfo);
	}
	
	@Override
	public void updateUserInfo(long id,String username){
		stuInfoDao.updateUserInfo(id,username);
	}
	
	@Override
	public void delete(Long id){
		stuInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		stuInfoDao.deleteBatch(ids);
	}
	
	@Override
	public void deleteUser(Long id){
		stuInfoDao.deleteUser(id);
	}

	@Override
	public List findStuByNo(Integer cId, Integer classid) {
		return stuInfoDao.findStuByNo(cId,classid);
	}

	@Override
	public void deleteBatchByClassId(Object[] ids) {
		stuInfoDao.deleteBatchByClassId(ids);
	}

}
