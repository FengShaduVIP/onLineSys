package com.twp.utils;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CallShell {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 编译代码
	 * @param workPath 临时文件根目录路径
	 * @param shellPath	shell 命令
	 * @return
	 * @throws IOException
	 */
	public String LinuxJudge(String workPath,String shellPath) throws IOException{
		logger.info("开始编译 代码"+workPath+" 文件里面的文件名字");
		Runtime rt = Runtime.getRuntime();
		File dir = new File(workPath);
		String str[] = { "sh", "-c", "/bin/sh " + shellPath };
		Process pcs = null ;
		try{
			pcs = rt.exec(str, null, dir);
			pcs.waitFor();
		}catch (Exception e){
			try{
				pcs.getErrorStream().close();
				pcs.getInputStream().close();
				pcs.getOutputStream().close();
			}
			catch(Exception ee){}
			e.printStackTrace();
		}finally{
			pcs.destroy();
		}
		String result = OperateFile.readTiJiao(workPath+"/comp_log");
		logger.info("编译代码  comp_log内容---》"+result);
		return result;
	}

	/**
	 *
	 * @param filePath 临时文件根目录路径
	 * @param shellPath shell 命令
	 * @return
	 * @throws IOException
	 */
	public String Linux(String filePath,String shellPath) throws IOException{
		Runtime rt = Runtime.getRuntime();
		File dir = new File(filePath);
		String str[] = { "sh", "-c", "/bin/sh " + shellPath };
		Process pcs = null ;
		try{
			pcs = rt.exec(str, null, dir);
			pcs.waitFor();
		}catch (Exception e){
			try{
				pcs.getErrorStream().close();
				pcs.getInputStream().close();
				pcs.getOutputStream().close();
			}
			catch(Exception ee){}
		}finally{
			pcs.destroy();
		}
		OperateFile reade = new OperateFile();
		String result = reade.readTiJiao(filePath+File.separator+"sim_log");
		logger.info("执行代码  sim_log内容---》"+result);
		return result;
	}
}