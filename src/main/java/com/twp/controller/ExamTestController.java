package com.twp.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.twp.entity.ClassTestEntity;
import com.twp.entity.ExamTestEntity;
import com.twp.service.ClassTestService;
import com.twp.service.ExamTestService;
import com.twp.utils.DateUtils;
import com.twp.utils.PageUtils;
import com.twp.utils.R;
import com.twp.utils.ShiroUtils;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 15:21:26
 */
@Controller
@RequestMapping("examtest")
public class ExamTestController {
	@Autowired
	private ExamTestService examTestService;
	@Autowired
	private ClassTestService classTestService;
	
	@RequestMapping("/examtest.html")
	public String list(){
		return "examtest/examtest.html";
	}

	@RequestMapping("/examTestHis.html")
	public String examTestHis(){
		return "examtest/examTestHis.html";
	}
	
	@RequestMapping("/examtest_add.html")
	public String add(){
		return "examtest/examtest_add.html";
	}
	
	/**
	 * 查询所有考试列表
	 */
	@ResponseBody
	@RequestMapping("/list")
//	@RequiresPermissions("examtest:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ExamTestEntity> examTestList = examTestService.queryList(map);
		int total = examTestService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(examTestList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 考试的题目列表查询
	 */
	@ResponseBody
	@RequestMapping("/examTestList")
//	@RequiresPermissions("examtest:list")
	public R examTestList(Integer id){
		//查询列表数据
		List<Map<String, String>> examTestList = examTestService.queryExamTestList(id);
		return R.ok().put("list", examTestList);
	}
	
	/**
	 * 查询正在考试列表
	 */
	@ResponseBody
	@RequestMapping("/isGoingList")
//	@RequiresPermissions("examtest:list")
	public R isGoingList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ExamTestEntity> examTestList = examTestService.queryIsGoingList(map);
		int total = examTestService.queryIsGoingTotal(map);
		
		PageUtils pageUtil = new PageUtils(examTestList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("examtest:info")
	public R info(@PathVariable("id") Integer id){
		ExamTestEntity examTest = examTestService.queryObject(id);
		
		return R.ok().put("examTest", examTest);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
//	@RequiresPermissions("examtest:save")
	public R save(@RequestBody ExamTestEntity examTest){
		Timestamp now = new Timestamp(System.currentTimeMillis());
		examTest.setStartTime(now);
		examTest.setStatus(1);
		examTest.setAuthorId((String) ShiroUtils.getSessionAttribute("username"));
		examTest.setCreateTime(DateUtils.time());
		examTestService.save(examTest);
		ClassTestEntity classTest = new ClassTestEntity();
		Integer examTestId = examTest.getId();
		List<String> classIds = examTest.getClassIds();
		for(int i = 0; i<classIds.size(); i++) {
			String classId = classIds.get(i);
			classTest.setClassId(Integer.parseInt(classId));
			classTest.setExamTestId(examTestId);
			classTestService.save(classTest);
		}
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("examtest:update")
	public R update(@RequestBody ExamTestEntity examTest){
		examTestService.update(examTest);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("examtest:delete")
	public R delete(@RequestBody Integer[] ids){
		examTestService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
