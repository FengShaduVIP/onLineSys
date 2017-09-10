package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.twp.dao.StuGradeDao;
import com.twp.entity.StuGradeEntity;
import com.twp.service.StuGradeService;



@Service("stuGradeService")
public class StuGradeServiceImpl implements StuGradeService {
	@Autowired
	private StuGradeDao stuGradeDao;
	
	@Override
	public StuGradeEntity queryObject(Integer id){
		return stuGradeDao.queryObject(id);
	}
	
	@Override
	public List<StuGradeEntity> queryList(Map<String, Object> map){
		return stuGradeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuGradeDao.queryTotal(map);
	}
	
	@Override
	public void save(StuGradeEntity stuGrade){
		stuGradeDao.save(stuGrade);
	}
	
	@Override
	public void update(StuGradeEntity stuGrade){
		stuGradeDao.update(stuGrade);
	}
	
	@Override
	public void delete(Integer id){
		stuGradeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		stuGradeDao.deleteBatch(ids);
	}
	
}
