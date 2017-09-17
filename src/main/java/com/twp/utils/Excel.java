package com.twp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 所需jar包 下载地址http://www.apache.org/dyn/closer.cgi/poi/release/bin/poi-bin-3.9-
 * 20121203.zip
 * 
 * poi-3.7-20101029.jar poi-ooxml-3.7-20101029.jar
 * poi-ooxml-schemas-3.7-20101029.jar xmlbeans-2.3.0.jar dom4j-1.6.1.jar
 * 
 * 
 */
public class Excel {
	private static final String CONFIG_FILE = "upload.properties";

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
			//logger.error("reading `" + CONFIG_FILE + "` error!");
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}
	
	
	/**
	 * 对外提供读取excel 的方法
	 * */
	public static Map<String, List<List<Object>>> readExcel(String fileName) {
		File file = new File(fileName);
		String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
				.substring(fileName.lastIndexOf(".") + 1);
		try {
			if ("xls".equals(extension)) {
				return read2003Excel(file);
			} else if ("xlsx".equals(extension)) {
				return read2007Excel(file);
			} else {
				throw new IOException("不支持的文件类型");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取 office 2003 excel
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static Map<String, List<List<Object>>> read2003Excel(File file)
			throws IOException {
		Map<String, List<List<Object>>> map = new HashMap<String, List<List<Object>>>();
		HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
		Object value = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		for (int page = 0; page < hwb.getNumberOfSheets(); page++) {
			// 第page页
			HSSFSheet sheet = hwb.getSheetAt(page);
			List<List<Object>> list = new ArrayList<List<Object>>();
			for (int i = sheet.getFirstRowNum(); i <= sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> linked = new LinkedList<Object>();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add(null);
						continue;
					}
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
																// String
					DecimalFormat nf = new DecimalFormat("0");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle()
								.getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle()
								.getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
					if (value == null || "".equals(value)) {
						continue;
					}
					linked.add(value);
				}
				list.add(linked);
			}
			map.put("page_" + (page + 1), list);
		}
		return map;
	}

	/**
	 * 读取Office 2007 excel
	 * */
	private static Map<String, List<List<Object>>> read2007Excel(File file)
			throws IOException {
		Map<String, List<List<Object>>> map = new HashMap<String, List<List<Object>>>();
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
		Object value = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		for (int page = 0; page < xwb.getNumberOfSheets(); page++) {
			// 第page页
			XSSFSheet sheet = xwb.getSheetAt(page);
			List<List<Object>> list = new ArrayList<List<Object>>();
			for (int i = sheet.getFirstRowNum(); i <= sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				List<Object> linked = new LinkedList<Object>();
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell = row.getCell(j);
					if (cell == null) {
						linked.add(null);
						continue;
					}
					DecimalFormat df = new DecimalFormat("0");// 格式化 number
																// String
																// 字符
					DecimalFormat nf = new DecimalFormat("0");// 格式化数字
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if ("@".equals(cell.getCellStyle()
								.getDataFormatString())) {
							value = df.format(cell.getNumericCellValue());
						} else if ("General".equals(cell.getCellStyle()
								.getDataFormatString())) {
							value = nf.format(cell.getNumericCellValue());
						}
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						value = "";
						break;
					default:
						value = cell.toString();
					}
					if (value == null || "".equals(value)) {
						continue;
					}
					linked.add(value);
				}
				list.add(linked);
			}
			map.put("page_" + (page + 1), list);
		}
		return map;
	}
}