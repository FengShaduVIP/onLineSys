package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twp.dao.ExamPaperDao;
import com.twp.entity.ExamPaperEntity;
import com.twp.service.ExamPaperService;

import java.util.List;
import java.util.Map;



@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {
	@Autowired
	private ExamPaperDao examPaperDao;
	
	@Override
	public ExamPaperEntity queryObject(Integer id){
		return examPaperDao.queryObject(id);
	}
	
	@Override
	public List<ExamPaperEntity> queryList(Map<String, Object> map){
		return examPaperDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return examPaperDao.queryTotal(map);
	}
	
	@Override
	public void save(ExamPaperEntity examPaper){
		examPaperDao.save(examPaper);
	}
	
	@Override
	public void update(ExamPaperEntity examPaper){
		examPaperDao.update(examPaper);
	}
	
	@Override
	public void delete(Integer id){
		examPaperDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		examPaperDao.deleteBatch(ids);
	}
	
}
