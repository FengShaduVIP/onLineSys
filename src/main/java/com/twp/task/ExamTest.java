package com.twp.task;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twp.entity.ExamTestEntity;
import com.twp.service.ExamTestService;

@Component("examTest")
public class ExamTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExamTestService examTestService;
	
	public void closeExamTest(){
		logger.info("执行关闭在线考试任务");
		List<ExamTestEntity> examTestList = examTestService.queryGoingList();
		if(examTestList != null && examTestList.size() > 0) {
			for(int i = 0;i<examTestList.size();i++) {
				Date endTime = examTestList.get(i).getEndTime();
				Date nowTime = new Date();
				if(endTime.before(nowTime))  {  //若 结束时间在当前时间之前 则改变status
					ExamTestEntity examTestEntity = examTestList.get(i);
					examTestEntity.setStatus(0);  //状态改变成  0   表示考试已结束。
					examTestService.update(examTestEntity);
				}
			}
		}
	}

}
