package com.twp.dao;

import com.twp.entity.ExamPaperEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-20 21:26:57
 */
public interface ExamPaperDao extends BaseDao<ExamPaperEntity> {

    List<Map<String,String>> queryList2(Map<String, Object> map);
}
