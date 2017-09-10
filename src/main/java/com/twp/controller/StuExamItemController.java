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

import com.twp.entity.StuExamItemEntity;
import com.twp.service.StuExamItemService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-09-09 22:55:25
 */
@Controller
@RequestMapping("stuexamitem")
public class StuExamItemController {
	@Autowired
	private StuExamItemService stuExamItemService;
	
	@RequestMapping("/stuexamitem.html")
	public String list(){
		return "stuexamitem/stuexamitem.html";
	}
	
	@RequestMapping("/stuexamitem_add.html")
	public String add(){
		return "stuexamitem/stuexamitem_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("stuexamitem:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<StuExamItemEntity> stuExamItemList = stuExamItemService.queryList(map);
		int total = stuExamItemService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(stuExamItemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("stuexamitem:info")
	public R info(@PathVariable("id") Integer id){
		StuExamItemEntity stuExamItem = stuExamItemService.queryObject(id);
		
		return R.ok().put("stuExamItem", stuExamItem);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("stuexamitem:save")
	public R save(@RequestBody StuExamItemEntity stuExamItem){
		stuExamItemService.save(stuExamItem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("stuexamitem:update")
	public R update(@RequestBody StuExamItemEntity stuExamItem){
		stuExamItemService.update(stuExamItem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("stuexamitem:delete")
	public R delete(@RequestBody Integer[] ids){
		stuExamItemService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
