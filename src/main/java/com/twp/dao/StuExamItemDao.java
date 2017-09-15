package com.twp.dao;

import com.twp.entity.StuExamItemEntity;

import java.util.List;

/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 22:55:25
 */
public interface StuExamItemDao extends BaseDao<StuExamItemEntity> {
	List<StuExamItemEntity> queryObjByValue(Integer stuId,Integer examTestId,Integer itemId);

	Integer queryStuSumScore(Integer stuId,Integer examTestId);

}
