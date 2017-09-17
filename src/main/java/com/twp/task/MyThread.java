package com.twp.task;

import com.twp.utils.OperateFile;
import com.twp.utils.PublicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mi amor on 2017/9/17.
 */
public class MyThread extends Thread {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run() {
        String prcaticePath = PublicUtils.getConfig("PRCATICE");
        int result = OperateFile.forceCleanFileBelowDirectory(prcaticePath);
        if(result==1){
            logger.info("文件目录："+prcaticePath+" 删除文件成功");
        }else{
            logger.error("文件目录："+prcaticePath+" 为空目录");
        }
    }
}
