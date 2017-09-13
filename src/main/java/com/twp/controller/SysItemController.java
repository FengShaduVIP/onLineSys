package com.twp.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.stereotype.Controller;

import com.twp.entity.StuExamItemEntity;
import com.twp.entity.SysItemEntity;
import com.twp.service.StuExamItemService;
import com.twp.service.SysItemService;
import com.twp.utils.DateUtils;
import com.twp.utils.FileUtils;
import com.twp.utils.OperateFile;
import com.twp.utils.PageUtils;
import com.twp.utils.PublicUtils;
import com.twp.utils.R;
import com.twp.utils.ShiroUtils;


@Controller
@RequestMapping("/sysitem")
public class SysItemController {
	@Autowired
	private SysItemService sysItemService;
	@Autowired
	private StuExamItemService stuExamItemService;
	
	
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
		return "onlineitem/itemList.html";
	}

    @RequestMapping("/itemDetail.html")
    public String itemDetail(){
        return "onlineitem/itemDetail.html";
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
		int total = sysItemService.queryTotal(map);
		
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
		String TI_KU = PublicUtils.getConfig("FILE_PATH")+File.separator+"TI_KU";
		String filePath = TI_KU+File.separator+sysItem.getId()+File.separator+sysItem.getName()+"_tb.v";
		sysItem.setTestBeach(FileUtils.readfile(filePath));
		return R.ok().put("sysItem", sysItem);
	}
	
	/**
	 * 提交题目
	 */
//	@ResponseBody
//	@RequestMapping("/tijiao")
//	public R tijiao(String Text, Integer ItemId,Integer examTestId) throws IOException {
//			Long userId = ShiroUtils.getUserId();  //登陆人id
//			String result[] = new String[2];
//			int isRight = 0;
//			Integer scorce = 0;
//			result = onlineTestService.BianYi(Text,ItemId,name);
//			if(result[1].contains("error")){
//				result[1] = "<br/>编译代码出错！！<br/><br/>"+result[1];
//			}else{
//				try {
//					OperateFile reade = new OperateFile();
//					String filePath = result[0]+File.separator+"run_sim.sh";
//					String read = reade.readfile(result[0]+File.separator+"run_sim.sh");
//					read = read +"vsim  +nospecify -c -lib work "+name+"_tb   -do 'run -all'";
//					File file = new File(filePath);
//					FileWriter out = new FileWriter(file);
//					out.write(read);
//					out.close();
//					
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				if(result[1].contains("Success")||result[1].contains("success")){//仿真通过
//					isRight = 1;
//					result[1] = "仿真成功。代码通过验证！！<br/><br/>";
//				}else if(result[1].contains("Error")||result[1].contains("error")){//仿真失败 代码错误
//					result[1] = "仿真失败。代码未通过验证！！<br/><br/>";
//				}else{
//					result[1] = "请刷新重新上传！";
//				}
//			}
//				StuExamItemEntity stuExamItem = new StuExamItemEntity();
//				stuExamItem.setExamPaperId(examPaperId);  //试卷id
//				stuExamItem.setItemId(itemId);            //题目id
//				stuExamItem.setStuId(stuId);              //登陆人id
//				stuExamItem.setScore(score);              //分数
//				stuExamItem.setCreateTime(new Date());    //创建时间
//				stuExamItem.setClassId(classId);          //班级id
//				stuExamItem.setExamTestId(examTestId);    //考试Id
//				stuExamItem.setStatus(status);            //状态
//				stuExamItemService.save(stuExamItem);
//			}
//		return R.ok();
//	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tech:items:save")
	public R save(HttpServletRequest request){
		SysItemEntity sysItem = new SysItemEntity();
		HttpSession session = request.getSession();
		sysItem.setAuthor(session.getAttribute("username")+"");
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
	
}
