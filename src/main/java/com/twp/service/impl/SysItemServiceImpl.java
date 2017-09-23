package com.twp.service.impl;

import com.twp.utils.CallShell;
import com.twp.utils.FileUtils;
import com.twp.utils.OperateFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twp.dao.SysItemDao;
import com.twp.entity.SysItemEntity;
import com.twp.service.SysItemService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("sysItemService")
public class SysItemServiceImpl implements SysItemService {

	private Logger logger = LoggerFactory.getLogger(getClass());

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
	public List<SysItemEntity> queryStuList(Map<String, Object> map){
		return sysItemDao.queryStuList(map);
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


	/**
	 * 更新题目学生是否看见
	 * @param ids
	 */
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


	/**
	 * 编译提交内容 返回 filePath 提交内容文件的路径，bianYiResult 编译是否通过
	 * @param text 提交内容
	 * @param itemName 题目编译所需名字
	 * @param itemId 题目ID
	 * @return
	 */
	@Override
	public Map<String,String> bianYi(String text, String itemName, Integer itemId) {
		Map<String,String> str = new HashMap<String,String>();
		try {
			boolean append = true;
			String filePath = OperateFile.writeFile(text, itemId,itemName,append);
			logger.info("bianYi获的 临时文件路径："+filePath);
			str.put("filePath",filePath);
			logger.info("创建 filelist文件");
			String filelist =filePath+ File.separator+"filelist" ;
			File file = new File(filelist);
			String line = System.getProperty("line.separator");
			FileWriter out = new FileWriter(file);
			out.write(itemName+".v");
			out.write(line);
			out.write(itemName+"_tb.v");
			out.close();

			String shellPath = "./compile_design.sh | tee 'comp_log'";
			CallShell shell_pre = new CallShell();
			String bianYiResult = shell_pre.LinuxJudge(filePath, shellPath);
			str.put("bianYiResult",bianYiResult);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str;
	}

	// 判断代码正误
	public String judge_run(String filepath){
		String readLog = "";
		String shellPath = "./run_sim.sh | tee 'sim_log'";
		CallShell shell_do = new CallShell();
		try {
			readLog = shell_do.Linux(filepath, shellPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readLog;
	}

	@Override
	public int queryStuTotal(Map<String, Object> map) {
		return sysItemDao.queryStuTotal(map);
	}

}
