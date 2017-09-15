package com.twp.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.twp.entity.SysUserEntity;
import com.twp.service.StuExamItemService;
import com.twp.utils.OperateFile;
import com.twp.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.twp.entity.SysItemEntity;
import com.twp.service.SysItemService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/onlinetest")
public class OnLineTestController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysItemService sysItemService;

	@Autowired
	private StuExamItemService stuExamItemService;


	
	@RequestMapping("/sysitem.html")
	public String list(){
		return "sysitem/sysitem.html";
	}


	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		List<SysItemEntity> sysItemList = sysItemService.queryList(map);
		int total = sysItemService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(sysItemList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 在线测试提交题目 并保存成绩
	 * @param sendData itemID 题目ID,submitContext 代码内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sumbitItemTest" , method = RequestMethod.POST)
	public R sumbitItemTest(@RequestBody Map<String,String> sendData){
		SysUserEntity userObj = ShiroUtils.getUserEntity();
		String submitContext = sendData.get("submitContext");
		Long userId = userObj.getUserId();
		int isRight = 0;
		Integer itemId = Integer.parseInt(sendData.get("itemId"));
		Integer examTestId = Integer.parseInt(sendData.get("examTestId"));
		SysItemEntity itemEntity = sysItemService.queryObject(itemId);
		int score = itemEntity.getScore();
		logger.info("用户："+userObj.getRealName()+"，提交在线测试题目："+itemEntity.getTitle()+"  题目ID"+itemEntity.getId());
		Integer scorce = itemEntity.getScore();
		String itemName = itemEntity.getName();//提交代码 编译文件名称
		Map<String,String> resultMap  = sysItemService.bianYi(submitContext,itemName,itemId);
		String bianYiResult = resultMap.get("bianYiResult");
		if(bianYiResult.contains("error")||bianYiResult.contains("ERROR")||bianYiResult.contains("Error")) {
			bianYiResult = "<br/>编译代码出错！！<br/><br/>" + bianYiResult;
			score = 0;
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
					score=0;
				}else{
					bianYiResult = "请刷新重新上传！";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		stuExamItemService.saveStuExamTestInfo(userId,itemId,examTestId,isRight,score);
		return R.ok().put("result",bianYiResult);
	}



}
