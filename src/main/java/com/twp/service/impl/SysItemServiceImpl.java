package com.twp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twp.dao.SysItemDao;
import com.twp.entity.SysItemEntity;
import com.twp.service.SysItemService;

import java.util.List;
import java.util.Map;



@Service("sysItemService")
public class SysItemServiceImpl implements SysItemService {
	@Autowired
	private SysItemDao sysItemDao;
	
	@Override
	public SysItemEntity queryObject(Integer id){
		return sysItemDao.queryObject(id);
	}
	
	@Override
	public List<SysItemEntity> queryList(Map<String, Object> map){
		return sysItemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysItemDao.queryTotal(map);
	}
	
	@Override
	public void save(SysItemEntity sysItem){
		sysItemDao.save(sysItem);
	}
	
	@Override
	public void update(SysItemEntity sysItem){
		sysItemDao.update(sysItem);
	}
	
	@Override
	public void delete(Integer id){
		sysItemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		sysItemDao.deleteBatch(ids);
	}

	@Override
	public void changeItems(Integer[] ids) {
		for(int i=0;i<ids.length;i++){
			SysItemEntity itemEntity = queryObject(ids[i]);
			String isVisible = itemEntity.getIsVisible();
			if("0".equals(isVisible)){
				itemEntity.setIsVisible("1");
			}else{
				itemEntity.setIsVisible("0");
			}
			update(itemEntity);
		}
		
	}

	@Override
	public String findLastId() {
		return sysItemDao.findLastId();
	}
	
}
