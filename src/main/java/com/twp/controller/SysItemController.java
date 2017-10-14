package com.twp.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.stereotype.Controller;

import com.twp.entity.StuExamItemEntity;
import com.twp.entity.StuInfoEntity;
import com.twp.entity.SysItemEntity;
import com.twp.entity.SysUserEntity;
import com.twp.service.StuExamItemService;
import com.twp.service.StuInfoService;
import com.twp.service.SysItemService;
import com.twp.service.SysUserService;
import com.twp.utils.DateUtils;
import com.twp.utils.Excel;
import com.twp.utils.FileUtils;
import com.twp.utils.OperateFile;
import com.twp.utils.PageUtils;
import com.twp.utils.PublicUtils;
import com.twp.utils.R;
import com.twp.utils.ShiroUtils;


@Controller
@RequestMapping("sysitem")
public class SysItemController {
	@Autowired
	private SysItemService sysItemService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private StuInfoService stuInfoService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/sysitem.html")
	public String list(){
		return "sysitem/sysitem.html";
	}
	
	@RequestMapping("/sysitem_add.html")
	public String add(){
		return "sysitem/sysitem_add.html";
	}
	
	@RequestMapping("/sysitem_edit.html")
	public String edit(){
		return "sysitem/sysitem_edit.html";
	}

	@RequestMapping("/stuItemList.html")
	public String stuItemList(){
		return "onlineitem/item_list.html";
	}

    @RequestMapping("/item_detail.html")
    public String itemDetail(){
        return "onlineitem/item_detail.html";
    }

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tech:items:list")
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

	@ResponseBody
	@RequestMapping("/listNoPage")
	public R listNoPage(){
		//查询列表数据
		List<SysItemEntity> sysItemList = sysItemService.queryListNoPage();
		int total = sysItemService.queryTotalNoPage();

		PageUtils pageUtil = new PageUtils(sysItemList, total, 0, 0);

		return R.ok().put("page", pageUtil);
	}


	
	/**
	 * 学生题目列表
	 */
	@ResponseBody
	@RequestMapping("/stuList")
	public R stuList(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<SysItemEntity> sysItemList = sysItemService.queryStuList(map);
		int total = sysItemService.queryStuTotal(map);
		
		PageUtils pageUtil = new PageUtils(sysItemList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	//@RequiresPermissions("tech:items:info")
	public R info(@PathVariable("id") Integer id){
		SysItemEntity sysItem = sysItemService.queryObject(id);
		String TI_KU = PublicUtils.getConfig("TI_KU");
		String filePath = TI_KU+File.separator+sysItem.getId()+File.separator+sysItem.getName()+"_tb.v";
		sysItem.setTestBeach(FileUtils.readfile(filePath));
		return R.ok().put("sysItem", sysItem);
	}

	/**
	 * excel导入
	 */
	/*@ResponseBody
	@RequestMapping("/importExcel")
	public R ImportExcel(HttpServletRequest request) {
		String classId = request.getParameter("classId");  //获取班级id
		Long userId = ShiroUtils.getUserId();              //获取登陆教师id
		Integer classid = Integer.parseInt(classId);
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile uploadFile =req.getFile("ImportFile");
		String path = FileUtils.saveFile(uploadFile,uploadFile.getOriginalFilename());  //获取到路径
		Map<String, List<List<Object>>> tMap = Excel.readExcel(path);
		List<String> msgList=new ArrayList<String>();
		String addclassid = null;
		int page_all=tMap.size();
		for(int page=1;page<=page_all;page++){
			int i=1;
			List<List<Object>> oList=tMap.get("page_"+page);
			if(oList.size()>0){
				oList.remove(0);
				for(List<Object> list:oList){
					i++;
					if(list.get(0)!=null){
						String tId=(String) list.get(0);
						Integer cId=Integer.valueOf(tId);   //获取学生id
						if(tId.matches("^[0-9]*$")){
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
								stuInfo.setTeachId(userId);
								stuInfoService.save(stuInfo);
							}else{
								msgList.add("第"+page+"页 -第"+i+"行 :已在班級中");
							}
						}else{
							msgList.add("学号："+cId+" 姓名："+(String)list.get(1)+" 在该班级已存在！");
						}
					}
				}
			}
			
		}
		return R.ok();
	}*/
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tech:items:save")
	public R save(HttpServletRequest request){
		SysItemEntity sysItem = new SysItemEntity();
		HttpSession session = request.getSession();
		sysItem.setAuthor(ShiroUtils.getUserId()+"");
		sysItem.setContext(request.getParameter("context"));
		sysItem.setLevel(request.getParameter("level"));
		sysItem.setTitle(request.getParameter("title"));
		sysItem.setName(request.getParameter("itemName"));
		if(PublicUtils.isNumeric(request.getParameter("score"))){
			sysItem.setScore(Integer.parseInt(request.getParameter("score")));
		}else{
			sysItem.setScore(0);
		}
		sysItem.setCreateTime(DateUtils.format(new Date()));
		sysItemService.save(sysItem);
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile uploadFile =req.getFile("ImportFile");
		String itemId = sysItemService.findLastId();
		String fileName = sysItem.getName();
		FileUtils.saveTestBenchFile(uploadFile, itemId, fileName);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tech:items:update")
	public R update(@RequestBody SysItemEntity sysItem){
		sysItemService.update(sysItem);
		try {
			FileUtils.updateFile(sysItem.getId()+"",sysItem.getName(),sysItem.getTestBeach());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tech:items:delete")
	public R delete(@RequestBody Integer[] ids){
		sysItemService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 更改题目状态
	 */
	@ResponseBody
	@RequestMapping("/change")
	@RequiresPermissions("tech:items:change")
	public R change(@RequestBody Integer[] ids){
		sysItemService.changeItems(ids);
		return R.ok();
	}

	/**
	 *在线练习页面 代码提交 测试
	 * @param sendData
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sumbitItem" , method = RequestMethod.POST)
	public R sumbitItem(@RequestBody Map<String,String> sendData){
		Map<String,Object> resultData = new HashMap<>();
		SysUserEntity userObj = ShiroUtils.getUserEntity();
		String submitContext = sendData.get("submitContext");
		Integer itemId = Integer.parseInt(sendData.get("itemId"));
		int isRight = 0;
		SysItemEntity itemEntity = sysItemService.queryObject(itemId);
		logger.info("用户："+userObj.getRealName()+"，提交在线测试题目："+itemEntity.getTitle()+"  题目ID"+itemEntity.getId());
		Integer scorce = itemEntity.getScore();
		String itemName = itemEntity.getName();//提交代码 编译文件名称
		Map<String,String> resultMap  = sysItemService.bianYi(submitContext,itemName,itemId);
		String bianYiResult = resultMap.get("bianYiResult");
		if(bianYiResult.contains("error")) {
			bianYiResult = "<br/>编译代码出错！！<br/><br/>" + bianYiResult;
			logger.info("编译出错：\r\n"+bianYiResult);
		}else {
			logger.info("编译通过");
			String filePath = resultMap.get("filePath");
			try {
				String fileNamePath = filePath+ File.separator+"run_sim.sh";
				String read = OperateFile.readfile(fileNamePath);
				read = read +"vsim  +nospecify -c -lib work "+itemName+"_tb   -do 'run -all'";
				File file = new File(fileNamePath);
				FileWriter out = new FileWriter(file);
				out.write(read);
				out.close();
				bianYiResult =sysItemService.judge_run(filePath);
				if(bianYiResult.contains("Success")||bianYiResult.contains("success")){//仿真通过
					isRight = 1;
					bianYiResult = "仿真成功。代码通过验证！！<br/><br/>";
				}else if(bianYiResult.contains("Error")||bianYiResult.contains("error")){//仿真失败 代码错误
					bianYiResult = "仿真失败。代码未通过验证！！<br/><br/>";
					isRight = 3;
				}else{
					bianYiResult = "请刷新重新上传！";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultData.put("result",bianYiResult);
		resultData.put("isRight",isRight+"");
		return R.ok(resultData);
	}
	
}
