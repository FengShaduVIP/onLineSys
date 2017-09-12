package com.twp.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.twp.entity.ExamTestEntity;
import com.twp.entity.SysUserEntity;
import com.twp.service.ExamTestService;
import com.twp.service.SysUserService;

public class ExamTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExamTestService examTestService;
	
	public void closeExamTest(String params){
		logger.info("我是带参数的test方法，正在被执行，参数为：" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		List<ExamTestEntity> examTestList = examTestService.queryGoingList();
		if(examTestList != null && examTestList.size() > 0) {
			for(int i = 0;i<examTestList.size();i++) {
				Date endTime = examTestList.get(i).getEndTime();
				Date nowTime = new Date();
				if(endTime.before(nowTime))  {  //若 结束时间在当前时间之前 则改变status
					ExamTestEntity examTestEntity = examTestList.get(i);
					examTestEntity.setStatus(0);  //状态改变成  0   表示考试已结束。
					examTestService.save(examTestEntity);
				}
			}
		}
//		SysUserEntity user = sysUserService.queryObject(1L);
//		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}
	
	
	public void test2(){
		logger.info("我是不带参数的test2方法，正在被执行");
	}

}
