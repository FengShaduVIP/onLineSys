package com.twp.service.impl;

import com.twp.dao.StuInfoDao;
import com.twp.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.twp.dao.StuExamItemDao;
import com.twp.entity.StuExamItemEntity;
import com.twp.service.StuExamItemService;



@Service("stuExamItemService")
public class StuExamItemServiceImpl implements StuExamItemService {
	@Autowired
	private StuExamItemDao stuExamItemDao;

	@Autowired
	private StuInfoDao stuInfoDao;
	
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
		Integer stuId = Integer.parseInt(userId.toString());
		List<StuExamItemEntity> entityList = this.queryObjByValue(stuId,examTestId,itemId);
		StuExamItemEntity stuExamObj = new StuExamItemEntity();
		if(entityList!=null&&entityList.size()>0){
			stuExamObj = entityList.get(0);
			stuExamObj.setStatus(isRight);
			stuExamObj.setCreateTime(new Date());
			stuExamObj.setScore(score);
			this.update(stuExamObj);
		}else{
			int classId = stuInfoDao.queryStuClass(userId);
			stuExamObj.setItemId(itemId);
			stuExamObj.setClassId(classId);
			stuExamObj.setStuId(stuId);
			stuExamObj.setCreateTime(new Date());
			stuExamObj.setScore(score);
			stuExamObj.setExamTestId(examTestId);
			stuExamObj.setStatus(isRight);
			this.save(stuExamObj);
		}
	}

	@Override
	public List<StuExamItemEntity> queryObjByValue(Integer userId, Integer examTestId, Integer itemId) {
		return stuExamItemDao.queryObjByValue(userId,examTestId,itemId);
	}

	/**
	 * 查询学生 某场考试总分数
	 * @param stuId
	 * @param examTestId
	 * @return
	 */
	@Override
	public int queryStuSumScore(Integer stuId, Integer examTestId) {
		return stuExamItemDao.queryStuSumScore(stuId,examTestId);
	}


}
