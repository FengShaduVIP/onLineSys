package com.twp.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twp.entity.ClassInfoEntity;
import com.twp.entity.SysUserEntity;
import com.twp.service.ClassInfoService;
import com.twp.service.SysUserRoleService;
import com.twp.service.SysUserService;

import com.twp.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.twp.entity.StuInfoEntity;
import com.twp.service.StuInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * 
 * @author tianweipeng
 * @email twp@gmail.com
 * @date 2017-08-05 15:59:28
 */
@Controller
@RequestMapping("/stuinfo")
public class StuInfoController {
	@Autowired
	private StuInfoService stuInfoService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ClassInfoService classInfoService;

	@RequestMapping("/stuinfo.html")
	public String list(){
		return "stuinfo/stuinfo.html";
	}
	
	@RequestMapping("/stuinfo_add.html")
	public String add(){
		return "stuinfo/stuinfo_add.html";
	}

	@RequestMapping("/importStu.html")
	public String importStu(){
		return "stuinfo/importStu.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	//@RequiresPermissions("stuinfo:list")
	public R list(Integer classId, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("classId",classId);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<Map<String,String>> stuInfoList = stuInfoService.queryList2(map);
		int total = stuInfoService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(stuInfoList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("stuinfo:info")
	public R info(@PathVariable("id") Long id){
		StuInfoEntity stuInfo = stuInfoService.queryObject(id);
		
		return R.ok().put("stuInfo", stuInfo);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/getStuInfo")
	@RequiresPermissions("stuinfo:restPassword")
	public R restPassword(Integer stuNo,Integer classId){
		List<Long> userRoleList = new ArrayList<Long>();
		userRoleList.add(3L);
		List<StuInfoEntity> objList = stuInfoService.findStuByNo(stuNo,classId);
		if(objList.size()>0){
			StuInfoEntity obj = objList.get(0);
			SysUserEntity userObj = sysUserService.queryObject(obj.getUserId());
			userObj.setPassword(stuNo+"");
			userObj.setRoleIdList(userRoleList);
			sysUserService.update(userObj);
		}
		return R.ok();
	}
	
	
	/**
	 * 根据学生id查询学生所在班级
	 */
	@ResponseBody
	@RequestMapping("/queryStuClass")
	//@RequiresPermissions("stuinfo:info")
	public R queryStuClass(){
		Long userId = ShiroUtils.getUserId();
		int classId = stuInfoService.queryStuClass(userId);
		return R.ok().put("classId", classId);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	//@RequiresPermissions("stuinfo:save")
	public R save(@RequestBody StuInfoEntity stuInfo){
		List<Long> userRoleList = new ArrayList<Long>();
		userRoleList.add(3L);
		//保存学生登陆用户信息
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setRealName(stuInfo.getStuName());
		userEntity.setPassword(stuInfo.getStuNo().toString());
		userEntity.setStatus(1);
		userEntity.setUsername(stuInfo.getStuNo()+"");
		userEntity.setLevel(0);
		userEntity.setRoleIdList(userRoleList);
		sysUserService.save(userEntity);
		Integer classId = stuInfo.getClassId();
		stuInfo.setUserId(userEntity.getUserId());
		ClassInfoEntity classInfoEntity = classInfoService.queryObject(classId);
		if(classInfoEntity!=null){
			stuInfo.setTeachId(classInfoEntity.getTeachId());
		}
		stuInfoService.save(stuInfo);
		//保存学生权限信息
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("stuinfo:update")
	public R update(@RequestBody StuInfoEntity stuInfo){
		Long id = stuInfo.getId();
		String username = stuInfo.getStuName();
		stuInfoService.updateUserInfo(id,username);
		stuInfoService.update(stuInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	//@RequiresPermissions("stuinfo:delete")
	public R delete(@RequestBody Long[] ids){
		for(int i = 0;i < ids.length;i++) {
			Long id = ids[i];
			stuInfoService.deleteUser(id);
		}
		stuInfoService.deleteBatch(ids);
		return R.ok();
	}

	/**
	 * 学生导入模板下载
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/fileDownload")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response){
		String fileName = "学生导入模板.xls".toString(); // 文件的默认保存名
		// 读到流中
		InputStream inStream = null;// 文件的存放路径
		try {
			if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileName = URLEncoder.encode(fileName, "UTF-8");
			} else {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
			String filePath = UploadUtils.getConfig("DownLoad")+ File.separator+"fileDownload.xls";
			inStream = new FileInputStream(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.reset();
		response.setContentType("application/x-excel");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
