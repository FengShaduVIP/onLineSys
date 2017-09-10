package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.twp.dao.ClassTestDao;
import com.twp.entity.ClassTestEntity;
import com.twp.service.ClassTestService;



@Service("classTestService")
public class ClassTestServiceImpl implements ClassTestService {
	@Autowired
	private ClassTestDao classTestDao;
	
	@Override
	public ClassTestEntity queryObject(Integer id){
		return classTestDao.queryObject(id);
	}
	
	@Override
	public List<ClassTestEntity> queryList(Map<String, Object> map){
		return classTestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classTestDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassTestEntity classTest){
		classTestDao.save(classTest);
	}
	
	@Override
	public void update(ClassTestEntity classTest){
		classTestDao.update(classTest);
	}
	
	@Override
	public void delete(Integer id){
		classTestDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		classTestDao.deleteBatch(ids);
	}
	
}
