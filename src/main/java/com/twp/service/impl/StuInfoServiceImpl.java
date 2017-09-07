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
	public List<StuInfoEntity> queryList(Map<String, Object> map){
		return stuInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuInfoDao.queryTotal(map);
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
	public void delete(Long id){
		stuInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		stuInfoDao.deleteBatch(ids);
	}
	
}
