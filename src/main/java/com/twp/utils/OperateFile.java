package com.twp.utils;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperateFile {
	private static final String CONFIG_FILE = "upload.properties";

	private static Logger logger = LoggerFactory.getLogger(OperateFile.class);
	//练习题目提交后显示加<br>换行
	public static String readTiJiao(String path) // 从文本文件中读取内容
	{
		String readStr = "";
		try {
			File file = new File(path);
			FileReader fileread = new FileReader(file);
			BufferedReader bufread = new BufferedReader(fileread);
			String read = null;
			while ((read = bufread.readLine()) != null) {
				readStr = readStr+ read+"</br>";
			}
		} catch (Exception d) {
			System.out.println(d.getMessage());
		}
		return readStr; // 返回从文本文件中读取内容
	}

	// 从文本文件中读取内容
	public static String readfile(String path){
		logger.info("文件路径："+path);
		String readStr = "";
		try {
			File file = new File(path);
			FileReader fileread = new FileReader(file);
			BufferedReader bufread = new BufferedReader(fileread);
			String read = null;
			while ((read = bufread.readLine()) != null) {
				readStr = readStr+ read+'\n';
			}
			logger.info("文件内容："+readStr);
		} catch (Exception d) {
			logger.debug("读取文件出错：",d.getMessage());
		}
		return readStr; // 返回从文本文件中读取内容
	}
	
	public static void copyFolder(String oldPath,String newPath)  {
		   logger.info("复制文件从："+oldPath);
		   logger.info("复制到："+newPath);
	       try  {    
	           (new  File(newPath)).mkdirs();  //如果文件夹不存在  则建立新文件夹    
	           File  a=new  File(oldPath);    
	           String[]  file=a.list();    
	           File  temp=null;    
	           for  (int  i  =  0;  i  <  file.length;  i++)  {    
	               if(oldPath.endsWith(File.separator)){    
	                   temp=new  File(oldPath+file[i]);    
	               }else{
	                   temp=new  File(oldPath+File.separator+file[i]);    
	               }    
	   				if(temp.isFile()){
	                   FileInputStream  input  =  new  FileInputStream(temp);    
	                   FileOutputStream  output  =  new  FileOutputStream(newPath  +  File.separator  +   
	                           (temp.getName()).toString());    
	                   byte[]  b  =  new  byte[1024  *  5];    
	                   int  len;    
	                   while  (  (len  =  input.read(b))  !=  -1)  {    
	                       output.write(b,  0,  len);    
	                   }    
	                   output.flush();    
	                   output.close();    
	                   input.close();    
	               }    
	               if(temp.isDirectory()){//如果是子文件夹    
	                   copyFolder(oldPath+File.separator+file[i],newPath+File.separator+file[i]);    
	               }    
	           }    
	       }    
	       catch  (Exception  e)  {    
	           System.out.println("复制整个文件夹内容操作出错");    
	           e.printStackTrace();    
	       }    
			logger.info("复制文件结束");
	   }    
	

	// Linux 写文件
	// 向文本文件中写入内容
	//返回临时文件路径 "../(n2s)/demo/"
	public static String writeFile(String content,Integer ItemId,String name, boolean append) throws IOException,
			InterruptedException {
		// 获取练习题临时保存路径
		String fileName = n2s(DateUtils.time());
		String practicePath = getConfig("PRCATICE") + File.separator+ fileName+File.separator+"demo"+File.separator;
		logger.info("获取文件临时保存路径："+practicePath);
		String name_1 = name+".v";
		String name_2 = name+"_tb.v";
		File writefile = new File(practicePath+name_1);
		String workPath = getConfig("DEMO");
		logger.info("获取DEMO路径："+workPath);
		writefile.setExecutable(true);// 设置可执行权限
		writefile.setReadable(true);// 设置可读权限
		writefile.setWritable(true);// 设置可写权限
		if (!writefile.getParentFile().exists()) {
			writefile.getParentFile().mkdirs();
		}
		boolean addStr = append;
		FileWriter filewriter = new FileWriter(writefile, addStr);
		BufferedWriter bufwriter = new BufferedWriter(filewriter);
		filewriter.write(content);
		filewriter.flush();
		filewriter.close();
		logger.info("上传内容保存到文件目录："+writefile);
		copyFolder(workPath, practicePath);

		String ItemPath = UploadUtils.getConfig("TI_KU")+File.separator+ItemId+File.separator+name_2;
		logger.info("题目目标文件路径："+ItemPath);
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "cp "+ItemPath+" "+practicePath});
            int exitCode = process.waitFor();
            File file = new File(ItemPath);
            file.setExecutable(true);// 设置可执行权限
			file.setReadable(true);// 设置可读权限
			file.setWritable(true);// 设置可写权限
			logger.info("题目目标文件复制到临时文件路径里面");
        } catch (Exception e) {
			try{
				process.getErrorStream().close();
				process.getInputStream().close();
				process.getOutputStream().close();
			}
			catch(Exception ee){}
            System.err.println("NullPointerException " + e.getMessage());
        }
		return practicePath;
	}


	/**
	 * 把10位时间戳数字转成字符串(混合大小写)
	 * 
	 * @param num
	 * @return
	 */
	private static String n2s(int num) {
		String str = "";
		char _char;
		String numStr = String.valueOf(num);
		for (int i = 0; i < numStr.length(); i++) {
			int n = Integer.parseInt(numStr.substring(i, i + 1));
			if (n <= 2) { // 这里最后一组只保留xyz不转换，用来做分隔符
				_char = (char) (n + 65 + rand(0, 3) * 10 + rand(0, 2) * 32);
			} else {
				_char = (char) (n + 65 + rand(0, 2) * 10 + rand(0, 2) * 32);
			}
			str += _char;
		}
		return str;
	}

	/**
	 * 产生随机整数,范围[m,n)
	 * 
	 * @return
	 */
	private static int rand(int m, int n) {
		return (int) (Math.random() * (n - m) + m);
	}

	/**
	 * 获取配置文件的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		ClassLoader loader = OperateFile.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(CONFIG_FILE);
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	/**使用dos命令强力删除文件
	 * 强力删除文件
	 * file 需要删除的文件
	 * return   如果目录不存在,则返回"目录不存在";
	 *          删除成功,返回ok;
	 *          删除失败 ,返回失败原因
	 *
	 */
	public static String forceDeleteFile(String file){
		File tagFile=new File(file);
		if(tagFile.exists()){
			try {
				String cmd = "cmd /c del "+file;
				Runtime rt = Runtime.getRuntime(); // 获取运行时系统
				Process proc = rt.exec(cmd); // 执行命令
				InputStream stderr =  proc.getInputStream(); // 获取输入流
				InputStreamReader isr = new InputStreamReader(stderr,"gbk");
				BufferedReader br = new BufferedReader(isr);
				String line = null;
                /*while ((line = br.readLine()) != null) { // 打印出命令执行的结果
                System.out.println(line);
            }*/
			} catch (Throwable t) {
				t.printStackTrace();
				return t.getMessage();
			}
			return "ok";
		}else {
			return "目录不存在";
		}
	}



	/**使用dos命令强力删除指定文件夹下的文件或者文件夹
	 *
	 * file 需要删除的文件
	 * return   如果目录不存在,则返回"目录不存在";
	 *          删除成功,返回ok;
	 *          删除失败 ,返回失败原因
	 *
	 */
	public static int forceCleanFileBelowDirectory(String directory){
		File tagFile=new File(directory);
		if(tagFile.exists()){
			logger.info("进入文件："+directory);
			if(tagFile.isDirectory()){
				logger.info("文件是文件夹");
				//是目录,遍历一层,遇鬼杀鬼,遇魔降魔
				File[] files=tagFile.listFiles();
				for(File file : files){
					if(file.isDirectory()){
						forceDeleteDirectory(file.getAbsolutePath());
					}else {
						forceDeleteFile(file.getAbsolutePath());
					}
				}
			}
			return 1;
		}else {
			return 0;
		}
	}

	/**使用dos命令强力删除目录
	 * 强力删除文件夹,里面就算有子文件夹,隐藏的,只读的,都能够全部删除掉.
	 * directory 需要删除的目录
	 * return 如果目录不存在,则返回"目录不存在";删除成功,返回ok;删除失败 ,返回失败原因
	 *
	 */
	public static String forceDeleteDirectory(String directory){
		File tagFile=new File(directory);
		if(tagFile.exists()){
			try {
				String cmd = "cmd /c rd "+directory+" /s/q";
				Runtime rt = Runtime.getRuntime(); // 获取运行时系统
				Process proc = rt.exec(cmd); // 执行命令
				InputStream stderr =  proc.getInputStream(); // 获取输入流
				InputStreamReader isr = new InputStreamReader(stderr,"gbk");
				BufferedReader br = new BufferedReader(isr);
				String line = null;
                /*while ((line = br.readLine()) != null) { // 打印出命令执行的结果
                System.out.println(line);
            }*/
			} catch (Throwable t) {
				t.printStackTrace();
				return t.getMessage();
			}
			return "ok";
		}else {
			return "目录不存在";
		}
	}


}