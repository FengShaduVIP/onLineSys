package com.twp.service.impl;

import com.twp.dao.StuInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twp.dao.StuGradeDao;
import com.twp.entity.StuGradeEntity;
import com.twp.service.StuGradeService;



@Service("stuGradeService")
public class StuGradeServiceImpl implements StuGradeService {
	@Autowired
	private StuGradeDao stuGradeDao;

	@Autowired
	private StuInfoDao stuInfoDao;
	
	@Override
	public StuGradeEntity queryObject(Integer id){
		return stuGradeDao.queryObject(id);
	}
	
	@Override
	public List<StuGradeEntity> queryList(Map<String, Object> map){
		return stuGradeDao.queryList(map);
	}
	
	@Override
	public List<Map<String, Object>> StuGradeList(Map<String, Object> map){
		return stuGradeDao.StuGradeList(map);
	}
	
	@Override
	public List<Map<String,Object>> StuGradeLists(Map<String, Object> map){
		return stuGradeDao.StuGradeLists(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return stuGradeDao.queryTotal(map);
	}
	
	@Override
	public int queryStuTotal(Map<String, Object> map){
		return stuGradeDao.queryStuTotal(map);
	}
	
	@Override
	public int queryStuTotals (Map<String, Object> map){
		return stuGradeDao.queryStuTotals(map);
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


	/**
	 * 保存学生在线考试分数
	 * @param userId
	 * @param examTestId
	 */
	@Override
	public void saveStuGrade(Long userId, Integer examTestId,Integer stuSumScore) {
		StuGradeEntity stuGradeEntity = this.queryObjByMap(userId,examTestId);
		if(stuGradeEntity!=null&&stuGradeEntity.getId()!=null){
			stuGradeEntity.setCreateTime(new Date());
			if(stuSumScore ==null){
				stuGradeEntity.setScore(0);
			}else{
				stuGradeEntity.setScore(stuSumScore);
			}
			this.update(stuGradeEntity);
		}else{
			int classId = stuInfoDao.queryStuClass(userId);
			if(stuSumScore ==null){
				stuGradeEntity.setScore(0);
			}else{
				stuGradeEntity.setScore(stuSumScore);
			}
			stuGradeEntity.setCreateTime(new Date());
			stuGradeEntity.setClassId(classId);
			stuGradeEntity.setExamTestId(examTestId);
			stuGradeEntity.setStuId(Integer.parseInt(userId.toString()));
			this.save(stuGradeEntity);
		}
	}

	/**
	 * 根据 uerID 和 考试ID  查询 学生成绩对象
	 * @param stuId
	 * @param examTestId
	 * @return
	 */
	@Override
	public StuGradeEntity queryObjByMap(Long stuId, Integer examTestId) {
		Map<String,String> map = new HashMap<>();
		map.put("stuId",stuId.toString());
		map.put("examTestId",examTestId.toString());
		return stuGradeDao.queryObjByMap(map);
	}

}
