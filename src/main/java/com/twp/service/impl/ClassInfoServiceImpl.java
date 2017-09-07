package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.twp.dao.ClassInfoDao;
import com.twp.entity.ClassInfoEntity;
import com.twp.service.ClassInfoService;



@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {
	@Autowired
	private ClassInfoDao classInfoDao;
	
	@Override
	public ClassInfoEntity queryObject(String classId){
		return classInfoDao.queryObject(classId);
	}
	
	@Override
	public List<ClassInfoEntity> queryList(Map<String, Object> map){
		return classInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassInfoEntity classInfo){
		classInfoDao.save(classInfo);
	}
	
	@Override
	public void update(ClassInfoEntity classInfo){
		classInfoDao.update(classInfo);
	}
	
	@Override
	public void delete(String classId){
		classInfoDao.delete(classId);
	}
	
	@Override
	public void deleteBatch(String[] classIds){
		classInfoDao.deleteBatch(classIds);
	}
	
}
