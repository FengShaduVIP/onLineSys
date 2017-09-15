package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.twp.dao.StuExamItemDao;
import com.twp.entity.StuExamItemEntity;
import com.twp.service.StuExamItemService;



@Service("stuExamItemService")
public class StuExamItemServiceImpl implements StuExamItemService {
	@Autowired
	private StuExamItemDao stuExamItemDao;
	
	@Override
	public StuExamItemEntity queryObject(Integer id){
		return stuExamItemDao.queryObject(id);
	}
	
	@Override
	public List<StuExamItemEntity> queryList(Map<String, Object> map){
		return stuExamItemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuExamItemDao.queryTotal(map);
	}
	
	@Override
	public void save(StuExamItemEntity stuExamItem){
		stuExamItemDao.save(stuExamItem);
	}
	
	@Override
	public void update(StuExamItemEntity stuExamItem){
		stuExamItemDao.update(stuExamItem);
	}
	
	@Override
	public void delete(Integer id){
		stuExamItemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		stuExamItemDao.deleteBatch(ids);
	}


	/**
	 * 保存 在线测试学生提交的题目信息 记录分数
	 * @param userId 用户ID
	 * @param itemId 题目ID
	 * @param examTestId 考试id 不是试卷ID
	 * @param isRight 是否正确
	 * @param score 所得分数
	 */
	@Override
	public void saveStuExamTestInfo(Long userId, Integer itemId, Integer examTestId, int isRight,int score) {

	}

}
