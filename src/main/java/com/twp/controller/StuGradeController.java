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

import com.twp.entity.StuGradeEntity;
import com.twp.service.StuGradeService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 23:02:45
 */
@Controller
@RequestMapping("stugrade")
public class StuGradeController {
	@Autowired
	private StuGradeService stuGradeService;
	
	@RequestMapping("/stugrade.html")
	public String list(){
		return "stugrade/stugrade.html";
	}
	
	@RequestMapping("/stugrade_add.html")
	public String add(){
		return "stugrade/stugrade_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("stugrade:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<StuGradeEntity> stuGradeList = stuGradeService.queryList(map);
		int total = stuGradeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(stuGradeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("stugrade:info")
	public R info(@PathVariable("id") Integer id){
		StuGradeEntity stuGrade = stuGradeService.queryObject(id);
		
		return R.ok().put("stuGrade", stuGrade);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("stugrade:save")
	public R save(@RequestBody StuGradeEntity stuGrade){
		stuGradeService.save(stuGrade);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stugrade:update")
	public R update(@RequestBody StuGradeEntity stuGrade){
		stuGradeService.update(stuGrade);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("stugrade:delete")
	public R delete(@RequestBody Integer[] ids){
		stuGradeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
