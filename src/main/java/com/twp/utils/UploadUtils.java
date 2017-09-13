package com.twp.utils;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传工具类 - 支持断点续传和flash控件上传
 * ;大文件上传需配合前端控件zuploader使用
 * @author Simon
 *
 */
public class UploadUtils {
	
	private static final String CONFIG_FILE = "upload.properties";
	
	/** when the has increased to 10kb, then flush it to the hard-disk. */
	private static final int BUFFER_LENGTH = 10240;
	
	private static final String MSG_RESUME_ERROR = "断点续传校验失败，请重新选择上传文件，或尝试重命名文件后再次上传";
	private static final String MSG_CREATE_ERROR = "临时文件创建失败，请检查保存目录权限";
	
	private static Logger logger = Logger.getLogger(UploadUtils.class);
	
	
	/**
	 * 获取tomcat的本地绝对路径,末位无斜杠
	 * @return
	 */
	public static String getWebAppsAbsPath(){
		String OS = System.getProperty("os.name").toLowerCase();//获取系统名称
	    String path = UploadUtils.class.getClassLoader().getResource("").getPath();
	    String contextPath = null;//SpringContextUtil.getContextPath();
	    //删除末尾的斜杠
	    if(contextPath.endsWith(File.separator)){
	    	contextPath = contextPath.substring(0,contextPath.length()-1);
	    }
	    //添加首位的斜杠
	    if(!contextPath.startsWith(File.separator) && OS.indexOf("win") < 0){
	    	contextPath = File.separator+contextPath;
	    }
	    String excludeString = File.separator+"WEB-INF"+File.separator+"classes"+File.separator;
	    path = path.substring(0, path.length() - excludeString.length());
	    //Maven的run的情况下不包含contextPath
	    //Maven run取到的值示例: /Users/Simon/Workspaces/MyEclipse%20Professional/shdyj-admin/src/main/webapp/WEB-INF/classes/
	    if(path.endsWith(contextPath)){
	    	path = path.substring(0, path.length() - contextPath.length());
	    }
	    if(OS.indexOf("win") >= 0 && path.startsWith("/")){
	    	path = path.substring(1).replace("/", "\\");
	    }
	    return path;
	}
	
	
	/**
	 * 获取配置文件的值
	 * @param key
	 * @return
	 */
	public static String getConfig(String key){
		ClassLoader loader = UploadUtils.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(CONFIG_FILE);
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			logger.error("reading `" + CONFIG_FILE + "` error!");
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
	//------快捷获取配置值-START-------
	/**
	 * 获取文件存储目录根路径,末位不含斜杠
	 * @return
	 */
	public static String getFileRepository() {
		String path = getConfig("FILE_REPOSITORY");
		
		if (path == null || path.isEmpty()){
			throw new NullPointerException("can not find value of FILE_SAVE_PATH in "+CONFIG_FILE);
		}
		if(getPathType().equals("relative")){ //采用相对路径
			path = getWebAppsAbsPath() + File.separator + path;
		}
		File dirFile = new File(path);
		if (!(dirFile.exists() && dirFile.isDirectory())) {
			//不存在则尝试自动创建
			if(!dirFile.mkdirs()){
				throw new NullPointerException(path+" folder does not exist");
			}
		}
		path = path.replace("%20", " ");
		return path;
	}
	
	/**
	 * 获取保存路径类型
	 * @return relative or absolute
	 */
	public static String getPathType(){
		String type = getConfig("PATH_TYPE").toLowerCase();
		if(!(type.equals("relative") || type.equals("absolute"))){
			type = "relative";
		}
		return type;
	}
	
	/**
	 * 获取文件服务器url,"http://"开头,末位不含斜杠
	 * @return
	 */
	public static String getFileServer() {
		String fileServer = getConfig("FILE_SERVER");
		if(fileServer.endsWith("/")){
			fileServer = fileServer.substring(0,fileServer.length()-1);
		}
		if(!fileServer.startsWith("http://")){
			fileServer = "http://" + fileServer;
		}
		return fileServer;
	}
	
	public static String getCrossServer() {
		return getConfig("CROSS_SERVER");
	}
	
	public static String getCrossOrigins() {
		return getConfig("CROSS_ORIGIN");
	}
	
	public static boolean isCrossed() {
		return Boolean.parseBoolean(getConfig("IS_CROSS"));
	}
	
	public static boolean isImageResize() {
		return Boolean.parseBoolean(getConfig("IMAGE_RESIZE"));
	}
	
	public static List<String> getList(String key){
		String extString = getConfig(key);
		List<String> list = Arrays.asList(extString.split(","));
		return list;
	}
	
	public static List<String> getVideoExtList(){
		return getList("VIDEO_EXT");
	}
	public static List<String> getImageExtList(){
		return getList("IMAGE_EXT");
	}
	
	
	//------快捷获取配置值-END--------
	
	/**
	 * 生成Token,即临时文件名称
	 * ;正常情况:TEMP_ 文件最后修改时间 + "_" + size的值 + 原扩展名
	 * ;modified=null的情况:TEMP_ name.HASHCODE + "_" + size的值 + 原扩展名
	 * ;size和modified一样，则生成的token一样
	 * @param name 文件名
	 * @param size 文件大小
	 * @param modified 文件最后修改时间
	 * @return
	 * @throws Exception
	 */
	public static String generateToken(String name, String size, String modified){
		if (size == null){
			return null;
		}
		String token = "TEMP_";
		if(modified == null){
			int code = name.hashCode();
			token += (code > 0 ? "A" : "B") + Math.abs(code);
		}else{
			token += modified;
		}
		token += "_" + size.trim() + getExtName(name);
		return token;
	}
	
	/**
	 * 获取Range参数
	 * @param range
	 * @return map contains keys - "from","to","size"
	 */
	public static Map<String,Long> parseRange(String range){
		if(range==null || range.isEmpty()){
			return null;
		}
		Matcher m = Pattern.compile("bytes \\d+-\\d+/\\d+").matcher(range);
		Map<String,Long> map = new HashMap<String,Long>();
		if (m.find()) {
			range = m.group().replace("bytes ", "");
			String[] rangeSize = range.split("/");
			String[] fromTo = rangeSize[0].split("-");

			Long from = Long.parseLong(fromTo[0]);
			Long to = Long.parseLong(fromTo[1]);
			Long size = Long.parseLong(rangeSize[1]);
			
			map.put("from", from);
			map.put("to",to);
			map.put("size",size);
			return map;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取需要断点续传的不完整的临时文件
	 * ;使用前请先验证token
	 * @param token
	 * @return 不存在则返回空的新文件
	 * @throws IOException 
	 */
	public static File getTokenedFile(String token) throws IOException {
		logger.warn("[getTokenedFile]"+getFileRepository() + File.separator + token);
		File f = new File(getFileRepository() + File.separator + token);
		if (!f.getParentFile().exists()){
			f.getParentFile().mkdirs();
		}
		if (!f.exists()){
			f.createNewFile();
		}
		return f;
	}
	
	/**
	 * 将上传的文件流保存成文件，直接传统上传和HTML5断点续传两种方式保存
	 * @param in
	 * @param token
	 * @param rangeHeaderString 【HTML5方式，传入Range请求头；传统方式传入null】
	 * @return {status:0,errorMsg:"",uploadedSize:0,saveName:""}
	 * 【status::-1:错误，0:初始状态，1:文件流上传完成(断点续传未全部传完)，2:文件已完成传输并重命名为新文件名】
	 * 【errorMsg::status=-1时的错误信息】
	 * 【uploadedSize::已上传的文件大小】
	 * 【saveName::status=2时的文件保存名，不含路径】
	 * TODO 传递InputStream会不会增加内容开销还需观察
	 */
	public static Map<String,Object> saveInputStream(InputStream in,String token,String rangeHeaderString){

		int status = 0;  //-1:错误，0:初始状态，1:文件流上传完成(断点续传未全部传完)，2:文件已完成传输并重命名为新文件名
		String errorMsg = ""; //status=-1时的错误信息
		long uploadedSize = 0; //已上传的文件大小
		String saveName = ""; //status=2时的文件保存名，不含路径
		
		Map<String,Long> range = UploadUtils.parseRange(rangeHeaderString);
		
		try {
			
			File f = UploadUtils.getTokenedFile(token);
			//如果是HTML5断点方式上传，则文件的现有大小要跟续传过来的开始位置大小进行匹配
			//如果是传统上传，则不需要验证
			if(range == null || (range != null && f.length()==range.get("from"))){ 
				
				InputStream content = in;
				OutputStream out = null;
				
				try {
					//保存临时文件
					if(range == null){
						out = new FileOutputStream(f);
					}else{
						out = new FileOutputStream(f, true); //断点续传追加
					}
					int read = 0;
					final byte[] bytes = new byte[BUFFER_LENGTH];
					while ((read = content.read(bytes)) != -1){
						out.write(bytes, 0, read);
					}
					uploadedSize = f.length(); //本次分片上传完后的文件大小
					status = 1; //本次分片上传完成
					
				} catch (IOException e) {
					//页面手动刷新可能导致输入流可能被强制中断
					status = -1;
					errorMsg = "Error: " + e.getLocalizedMessage();
					logger.error(errorMsg);
					e.printStackTrace();
				}finally {
					UploadUtils.close(out);
					UploadUtils.close(content);
				}
				
				//先判断本次输入流有无异常退出
				//传统上传则直接保存
				//HTML5整个文件的所有分片上传完成
				if (status == 1 && (range == null || (range != null && range.get("size") == uploadedSize))) {
					saveName = UploadUtils.generateNewSaveNameByToken(token);
					String newFullName = saveFile(f,saveName);
					status = 2;
					logger.warn("Token: `" + token + "`, newFullName: `" + newFullName + "`");
				}
				
			}else if(range != null && f.length()!=range.get("from")){
				status = -1;
				errorMsg = MSG_RESUME_ERROR;
			}
			
		} catch (IOException e1) {
			status = -1;
			errorMsg = MSG_CREATE_ERROR;
			e1.printStackTrace();
		}
		
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("status", status);
		map.put("errorMsg", errorMsg);
		map.put("uploadedSize", uploadedSize);
		map.put("saveName", saveName);
		
		return map;
		
	}
	
	public static Map<String,Object> saveMultipartFile(MultipartFile file,String... tokens){
		
		int status = 0;  //-1:错误，0:初始状态，2:文件已完成传输并重命名为新文件名
		String errorMsg = ""; //status=-1时的错误信息
		long uploadedSize = 0; //上传的文件大小
		String saveName = ""; //status=2时的文件保存名，不含路径
		
		if(!file.isEmpty()){
			InputStream in = null;
			OutputStream out = null;
			String token = "";
			if(tokens.length==1){
				token = tokens[0];
			}else{
				token = generateToken(file.getOriginalFilename(), String.valueOf(file.getSize()), null);
			}
			File f = null;
			
			try {
				in = file.getInputStream();
				f = UploadUtils.getTokenedFile(token);
				out = new FileOutputStream(f);
				int read = 0;
				final byte[] bytes = new byte[BUFFER_LENGTH];
				while ((read = in.read(bytes)) != -1){
					out.write(bytes, 0, read);
				}
				uploadedSize = f.length();
				status = 1;
			} catch (IOException e) {
				//页面手动刷新可能导致输入流可能被强制中断
				status = -1;
				errorMsg = "Error: " + e.getLocalizedMessage();
				logger.error(errorMsg);
				e.printStackTrace();
			}finally {
				UploadUtils.close(in);
				UploadUtils.close(out);
			}
			if(status==1){ //上传成功
				saveName = UploadUtils.generateNewSaveNameByToken(token);
				saveFile(f,saveName);
				status = 2;
				logger.warn("Token: `" + token + "`, NewName: `" + saveName + "`");
			}
		}else{
			errorMsg = "上传文件为空";
		}
		
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("status", status);
		map.put("errorMsg", errorMsg);
		map.put("uploadedSize", uploadedSize);
		map.put("saveName", saveName);
		
		return map;
		
	}
	
	
	/**
	 * 把上传完成的临时文件，保存到相应的目录，
	 * 返回文件全路径
	 * @param file
	 * @param newSaveName
	 * @return
	 */
	public static String saveFile(File file,String newSaveName){
		
		// TODO 获取扩展名,考虑采用扩展名建文件夹分类保存
		
		String fullFileName = getFullSavePath(newSaveName) + File.separator + newSaveName;
		
		//若文件存在则重新生成
		if(file.exists()){
			File dst = new File(fullFileName);
			file.renameTo(dst);
		}
		//System.out.println("上传文件完整路径fullFileName  ："+fullFileName);
		return fullFileName;
	}
	
	/**
	 * 根据Token生成新的文件保存名，不含路径 - 采用闲乐图片命名法
	 * @param token
	 * @return
	 */
	public static String generateNewSaveNameByToken(String token){
		String fileNameSuffix = "";
		//图片文件获取图片的尺寸信息
		if(parseFileKind(token).equals("image")){
			File imageFile = new File(getFileRepository() + File.separator + token);
			//生成图片尺寸后缀(图片宽度x图片高度.扩展名)
			try {
				BufferedImage bimg = ImageIO.read(imageFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				fileNameSuffix = width + "x" + height + getExtName(token);
			} catch (IOException e) {
				//获取图片信息失败，则按照原来的方式命名
				fileNameSuffix = token.substring(token.lastIndexOf("_")+1);
				logger.error("Error:"+e.getLocalizedMessage());
				e.printStackTrace();
			}
			
		}else{
			//直接截取toke的后缀(文件大小.扩展名)
			fileNameSuffix = token.substring(token.lastIndexOf("_")+1);
		}
		
		//根据上传完成的系统时间生成新的文件名
		String fileName = n2s(getNowTimestamp()) + "_!!" + fileNameSuffix;
		
		// TODO 验证生成的文件名是否重复
		//若文件存在则重新生成
//		if(file.exists()){
//			generateNewSaveNameByToken(token);
//		}
		return fileName;
	}
	
	
	
	/**
	 * 获取文件保存具体路径：文件仓库跟目录/文件种类目录(如video、image)/8位年月日
	 * ;文件种类目录根据文件扩展名自动创建
	 * ;日期目录根据当前时间自动创建
	 * @return
	 */
	public static String getFullSavePath(String saveName){
		//获取文件保存目录
		String savePath = getFileRepository();
		//获取当前年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		//获取文件种类
		String fileKind = parseFileKind(saveName);
		//拼装文件全路径
		String fullSavePath = savePath + File.separator + fileKind + File.separator + ymd;
		//检查并创建日期目录
		File dirFile = new File(fullSavePath);
		if (!(dirFile.exists() && dirFile.isDirectory())) {
			//不存在则尝试创建目录
			if(!dirFile.mkdirs()){
				throw new NullPointerException(fullSavePath+" folder does not exist");
			}
		}
		logger.warn("[getFullSavePath]"+fullSavePath);
		return fullSavePath;
	}
	
	/**
	 * 根据文件名返回文件种类
	 * ;目前就判断视频、图片和其他类型
	 * ;返回值"video","image","file"
	 * @param saveName
	 * @return
	 */
	public static String parseFileKind(String saveName){
		String fileExt = getExtName(saveName).substring(1);
		String fileKind = "file"; //默认是file种类
		if(getVideoExtList().contains(fileExt)){
			fileKind = "video";
		}
		if(getImageExtList().contains(fileExt)){
			fileKind = "image";
		}
		return fileKind;
	}
	
	/**
	 * 根据文件名解析出文件的weburl
	 * @param saveName 文件名
	 * @return 相对于tomcat根目录的url;若upload.properties的设置了PATH_TYPE为absolute则返回FILE_SERVER+"/"+文件url
	 */
	public static String parseFileUrl(String saveName){
		if(saveName==null || saveName.isEmpty()){
			return "";
		}
		//TODO 验证文件名格式
		//veuUIfkMNj_!!98546725.mp4
		String timestampStr = saveName.substring(0,10);
		int timestamp = s2n(timestampStr);
		//解析日期目录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date(timestamp*1000L));
		//获取文件种类
		String fileKind = parseFileKind(saveName);
		
		String fileUrl = fileKind + "/" + ymd + "/" + saveName;
		//如果是相对路径模式，则作为web路径加在文件路径中
		/*if(getPathType().equals("relative")){ //采用相对路径
			String path = getConfig("FILE_REPOSITORY");
			//删除末位斜杠
			if(path.endsWith(File.separator)){
				path = path.substring(0,path.length()-1);
			}
			//删除首位斜杠
			if(path.startsWith(File.separator)){
				path = path.substring(1);
			}
			fileUrl = "/" + path + "/" + fileUrl;
		}else{ //绝对路径
			fileUrl = getFileServer() + "/" + fileUrl;
		}*/
		String path = getConfig("FILE_SERVER");
		return path+fileUrl;
	}
	
	/**
	 * 根据文件名解析出文件URL
	 * @param saveName
	 * @param isAddContextPath
	 * @return
	 */
	public static String parseFileUrl(String saveName,boolean isAddContextPath){
		if(isAddContextPath){
			String contextPath = null;//SpringContextUtil.getContextPath() + "/";
			return contextPath + parseFileUrl(saveName);
		}else{
			return parseFileUrl(saveName);
		}
	}
	
	
	/**
	 * 把10位时间戳数字转成字符串(混合大小写)
	 * @param num
	 * @return
	 */
	private static String n2s(int num){
		String str = "";
		char _char;
		String numStr = String.valueOf(num);
        for(int i=0;i<numStr.length();i++){
            int n = Integer.parseInt(numStr.substring(i, i+1));
            if(n<=2){ //这里最后一组只保留xyz不转换，用来做分隔符
                _char = (char)(n+65+rand(0,3)*10+rand(0,2)*32);
            }else{
                _char = (char)(n+65+rand(0,2)*10+rand(0,2)*32);
            }
            str += _char;
        }
        return str;
	}
	
	/**
	 * 上面的逆运算
	 * @param str
	 * @return
	 */
	private static int s2n(String str){
		String numStr = "";
		str = str.toUpperCase();
		
		for(int i=0;i<str.length();i++){
			char _char = str.charAt(i);
			numStr += String.valueOf((((int)_char)-65)%10);
		}
        return Integer.parseInt(numStr);
	}
	
	/**
	 * 根据文件名返回文件扩展名,小写,包含小数点
	 */
	public static String getExtName(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	}
	
	/**
	 * 产生随机整数,范围[m,n)
	 * @return
	 */
	private static int rand(int m,int n){
		return (int)(Math.random()*(n-m)+m);
	}
	
	/**
	 * 获取当前时间的时间戳，单位秒
	 * @return
	 */
	public static int getNowTimestamp(){
		Long time = System.currentTimeMillis()/1000;
		return time.intValue();
	}
	
	
	/**
	 * close the IO stream.
	 * @param stream
	 */
	public static void close(Closeable stream) {
		try {
			if (stream != null)
				stream.close();
		} catch (IOException e) {
		}
	}
	
}
