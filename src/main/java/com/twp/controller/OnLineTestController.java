package com.twp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twp.entity.SysItemEntity;
import com.twp.service.SysItemService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


@Controller
@RequestMapping("/onlinetest")
public class OnLineTestController {

	@Autowired
	private SysItemService sysItemService;
	
	@RequestMapping("/sysitem.html")
	public String list(){
		return "sysitem/sysitem.html";
	}
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("onlinetest:items:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysItemEntity> sysItemList = sysItemService.queryList(map);
		int total = sysItemService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysItemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
}
