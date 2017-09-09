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

import com.twp.entity.ExampaperItemEntity;
import com.twp.service.ExampaperItemService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;

@Controller
@RequestMapping("/exampaperitem")
public class ExampaperItemController {
	@Autowired
	private ExampaperItemService exampaperItemService;
	
	@RequestMapping("/exampaperitem.html")
	public String list(){
		return "exampaperitem/exampaperitem.html";
	}
	
	@RequestMapping("/exampaperitem_add.html")
	public String add(){
		return "exampaperitem/exampaperitem_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("exampaperitem:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ExampaperItemEntity> exampaperItemList = exampaperItemService.queryList(map);
		int total = exampaperItemService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(exampaperItemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 试卷与题目关联列表查询
	 */
	@ResponseBody
	@RequestMapping("/examItemList")
	public R examItemList(Integer page, Integer limit,Integer exmaId){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("examId", exmaId);
		
		//查询列表数据
		List<Map<String, String>> exampaperItemList = exampaperItemService.queryItemInfo(map);
		int total = exampaperItemService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(exampaperItemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 保存试卷与题目关联数据
	 */
	@ResponseBody
	@RequestMapping("/saveExamItem")
	public R saveExamItem(@RequestBody Map map){
		Integer exmaId = (Integer) map.get("exmaId");
		Integer[] itemIds = (Integer[]) map.get("itemIds");
		for(int i = 0;i<itemIds.length ;i++) {
			Integer itemId = itemIds[i];
			ExampaperItemEntity exampaperItem = new ExampaperItemEntity();
			exampaperItem.setExampaperId(exmaId);
			exampaperItem.setItemId(itemId);
			exampaperItemService.save(exampaperItem);
		}
		return R.ok();
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("exampaperitem:info")
	public R info(@PathVariable("id") Integer id){
		ExampaperItemEntity exampaperItem = exampaperItemService.queryObject(id);
		
		return R.ok().put("exampaperItem", exampaperItem);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("exampaperitem:save")
	public R save(@RequestBody ExampaperItemEntity exampaperItem){
		exampaperItemService.save(exampaperItem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exampaperitem:update")
	public R update(@RequestBody ExampaperItemEntity exampaperItem){
		exampaperItemService.update(exampaperItem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("exampaperitem:delete")
	public R delete(@RequestBody Integer[] ids){
		exampaperItemService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
