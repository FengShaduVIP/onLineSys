package com.twp.task;

import com.twp.utils.OperateFile;
import com.twp.utils.PublicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mi amor on 2017/9/17.
 */
@Component("deleteFileTask")
public class FileTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void deleteFile(){

        logger.info("开始执行删除题目练习临时文档定时任务。。");
        MyThread myThread = new MyThread();
        myThread.start();

    }
}
