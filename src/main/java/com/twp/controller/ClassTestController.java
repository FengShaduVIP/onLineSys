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

import com.twp.entity.ClassTestEntity;
import com.twp.service.ClassTestService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-10 17:44:06
 */
@Controller
@RequestMapping("classtest")
public class ClassTestController {
	@Autowired
	private ClassTestService classTestService;
	
	@RequestMapping("/classtest.html")
	public String list(){
		return "classtest/classtest.html";
	}
	
	@RequestMapping("/classtest_add.html")
	public String add(){
		return "classtest/classtest_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("classtest:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ClassTestEntity> classTestList = classTestService.queryList(map);
		int total = classTestService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(classTestList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("classtest:info")
	public R info(@PathVariable("id") Integer id){
		ClassTestEntity classTest = classTestService.queryObject(id);
		
		return R.ok().put("classTest", classTest);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("classtest:save")
	public R save(@RequestBody ClassTestEntity classTest){
		classTestService.save(classTest);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("classtest:update")
	public R update(@RequestBody ClassTestEntity classTest){
		classTestService.update(classTest);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("classtest:delete")
	public R delete(@RequestBody Integer[] ids){
		classTestService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
