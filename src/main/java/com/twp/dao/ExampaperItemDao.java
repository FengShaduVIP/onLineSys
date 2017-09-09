package com.twp.dao;

import java.util.List;
import java.util.Map;

import com.twp.entity.ExampaperItemEntity;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-21 14:22:45
 */
public interface ExampaperItemDao extends BaseDao<ExampaperItemEntity> {
	
	List<Map<String, String>> queryItemInfo(Map<String, Object> map);
	
}
