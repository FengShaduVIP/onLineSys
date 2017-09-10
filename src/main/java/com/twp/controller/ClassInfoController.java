package com.twp.controller;

import java.util.*;

import com.twp.entity.SysUserEntity;
import com.twp.service.SysUserService;
import com.twp.utils.ShiroUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.twp.entity.ClassInfoEntity;
import com.twp.service.ClassInfoService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-07-03 16:25:50
 */
@Controller
@RequestMapping("classInfo")
public class ClassInfoController {
	@Autowired
	private ClassInfoService classInfoService;

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("/classInfo.html")
	public String list(){
		return "classinfo/classinfo.html";
	}
	
	@RequestMapping("/classinfo_add.html")
	public String add(){
		return "classinfo/classinfo_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	//@RequiresPermissions("classInfo:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ClassInfoEntity> classInfoList = classInfoService.queryList(map);
		int total = classInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(classInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 根据登陆人查询其所管理的班级列表
	 */
	@ResponseBody
	@RequestMapping("/listOnAdmin")
	//@RequiresPermissions("classInfo:list")
	public R listOnAdmin(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("teachId",ShiroUtils.getUserId());
		
		//查询列表数据
		List<Map<String, Object>> classInfoList = classInfoService.queryOnAdminList(map);
		int total = classInfoService.queryOnAdminTotal(map);
		
		PageUtils pageUtil = new PageUtils(classInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{classId}")
	//@RequiresPermissions("classInfo:info")
	public R info(@PathVariable("classId") Integer classId){
		ClassInfoEntity classInfo = classInfoService.queryObject(classId);
		
		return R.ok().put("classInfo", classInfo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody ClassInfoEntity classInfo){
		String username = ShiroUtils.getSessionAttribute("username").toString();
		SysUserEntity userObj = sysUserService.queryByUserName(username);
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		classInfo.setCreateYear(year);
		classInfo.setTeachId(userObj.getUserId());
		classInfoService.save(classInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("classInfo:update")
	public R update(@RequestBody ClassInfoEntity classInfo){
		classInfoService.update(classInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	//@RequiresPermissions("classInfo:delete")
	public R delete(@RequestBody String[] classIds){
		classInfoService.deleteBatch(classIds);
		
		return R.ok();
	}
	
}
