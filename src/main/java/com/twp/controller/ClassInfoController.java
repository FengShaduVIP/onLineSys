package com.twp.controller;

import java.util.*;

import com.twp.entity.StuInfoEntity;
import com.twp.entity.SysUserEntity;
import com.twp.service.StuInfoService;
import com.twp.service.SysUserService;
import com.twp.utils.*;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.twp.entity.ClassInfoEntity;
import com.twp.service.ClassInfoService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
	private StuInfoService stuInfoService;

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
		Long userId = ShiroUtils.getUserId();

		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		SysUserEntity userObj = ShiroUtils.getUserEntity();
		if(userObj.getLevel()!=2){
			map.put("teachId",userId);
		}
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
	public R listOnAdmin(){
		Map<String, Object> map = new HashMap<>();
		SysUserEntity userObj = ShiroUtils.getUserEntity();
		if(userObj.getLevel()!=2){
			map.put("teachId",ShiroUtils.getUserId());
		}

		//查询列表数据
		List<Map<String, Object>> classInfoList = classInfoService.queryOnAdminList(map);
		return R.ok().put("list", classInfoList);
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
	public R save(HttpServletRequest request, HttpServletResponse response){
		Long userId = ShiroUtils.getUserId();
		String className = request.getParameter("className").toString();
		String classNo = request.getParameter("classNo").toString();
		ClassInfoEntity classInfoEntity = new ClassInfoEntity();
		classInfoEntity.setClassName(className);
		classInfoEntity.setClassNo(classNo);
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		classInfoEntity.setCreateYear(year);
		classInfoEntity.setTeachId(userId);
		classInfoService.save(classInfoEntity);

		//获取文件
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile uploadFile =req.getFile("ImportFile");
		String path = FileUtils.saveFile(uploadFile,uploadFile.getOriginalFilename());  //获取到路径
		Map<String, List<List<Object>>> tMap = Excel.readExcel(path);
		List<String> msgList=new ArrayList<String>();
		Integer classid = classInfoEntity.getClassId();
		int page_all=tMap.size();
		for(int page=1;page<=page_all;page++){
			int i=1;
			List<List<Object>> oList=tMap.get("page_"+page);
			if(oList.size()>0){
				for(List<Object> list:oList){
					i++;
					if(list.get(0)!=null){
						String tId=(String) list.get(0);
						if(tId.matches("^[0-9]*$")){
							Long stuNo = Long.parseLong(tId);
							Integer cId=Integer.valueOf(tId);   //获取学生id
							Integer classNumber = Integer.valueOf((String) list.get(0));
							stuInfoService.deleteByStuNo(stuNo,Integer.parseInt(classNo));
							List lists = stuInfoService.findStuByNo(cId,classid);
							if(lists.size()==0){
								//保存到用户信息表中
								List<Long> userRoleList = new ArrayList<Long>();
								userRoleList.add(3L);
								SysUserEntity sysUser=new SysUserEntity();
								sysUser.setUsername(tId);   //用户名
								sysUser.setPassword(tId);   //用户密码
								sysUser.setRealName((String) list.get(1));   //用户姓名
//								sysUser.setEmail(email);         //电子邮件
//								sysUser.setMobile(mobile);       //手机号码
								sysUser.setStatus(1);            //设置用户状态 1 有效
								sysUser.setCreateTime(new Date());//用户创建时间
								sysUser.setLevel(0);             //用户等级 0表示学生
								sysUser.setRoleIdList(userRoleList);
								sysUserService.save(sysUser);
								//保存到学生信息表中
								StuInfoEntity stuInfo = new StuInfoEntity();
								stuInfo.setStuName((String) list.get(1));    //用户姓名
								stuInfo.setUserId(sysUser.getUserId());      //用户id
								stuInfo.setStuNo(cId);        //学号
								stuInfo.setClassId(classid);
								stuInfo.setClassNo(classNumber);
								stuInfo.setTeachId(userId);
								stuInfoService.save(stuInfo);
							}else{
								msgList.add("第"+page+"页 -第"+i+"行 :已在班級中");
							}
						}else{
							//msgList.add("学号："+cId+" 姓名："+(String)list.get(1)+" 在该班级已存在！");
						}
					}
				}
			}

		}

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

		stuInfoService.deleteBatchByClassId(classIds);
		
		return R.ok();
	}
	
}
