package com.twp.controller;

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
import org.springframework.stereotype.Controller;

import com.twp.entity.ExamPaperEntity;
import com.twp.service.ExamPaperService;
import com.twp.utils.PageUtils;
import com.twp.utils.R;


@Controller
@RequestMapping("/exampaper")
public class ExamPaperController {
	@Autowired
	private ExamPaperService examPaperService;
	
	@RequestMapping("/exampaper.html")
	public String list(){
		return "exampaper/exampaper.html";
	}
	
	@RequestMapping("/exampaper_add.html")
	public String add(){
		return "exampaper/exampaper_add.html";
	}
	
	@RequestMapping("/exam_item.html")
	public String Itemlist(){
		return "exampaper/exam_item.html";
	}

	@RequestMapping("/paperDetail.html")
	public String paperDetail(){
		return "exampaper/paperDetail.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tech:exampaper:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<ExamPaperEntity> examPaperList = examPaperService.queryList(map);
		int total = examPaperService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(examPaperList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tech:exampaper:info")
	public R info(@PathVariable("id") Integer id){
		ExamPaperEntity examPaper = examPaperService.queryObject(id);
		
		return R.ok().put("examPaper", examPaper);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tech:exampaper:save")
	public R save(@RequestBody ExamPaperEntity examPaper,HttpServletRequest request){
		HttpSession session = request.getSession();
		examPaper.setAuthorId(session.getAttribute("username")+"");
		examPaperService.save(examPaper);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tech:exampaper:update")
	public R update(@RequestBody ExamPaperEntity examPaper,HttpServletRequest request){
		HttpSession session = request.getSession();
		examPaper.setAuthorId(session.getAttribute("username")+"");
		examPaperService.update(examPaper);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tech:exampaper:delete")
	public R delete(@RequestBody Integer[] ids){
		examPaperService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
