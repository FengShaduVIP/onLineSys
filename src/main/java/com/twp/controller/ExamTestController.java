package com.twp.controller;

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

import com.twp.entity.ExamTestEntity;
import com.twp.service.ExamTestService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


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
	
	@RequestMapping("/examtest.html")
	public String list(){
		return "examtest/examtest.html";
	}
	
	@RequestMapping("/examtest_add.html")
	public String add(){
		return "examtest/examtest_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("examtest:list")
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
	@RequiresPermissions("examtest:save")
	public R save(@RequestBody ExamTestEntity examTest){
		examTestService.save(examTest);
		
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
