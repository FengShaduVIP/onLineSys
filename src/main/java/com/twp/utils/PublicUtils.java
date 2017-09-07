package com.twp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PublicUtils {

	private static Logger logger = Logger.getLogger(PublicUtils.class);

	public static String CONFIG_FILE = "config.properties";

	/**
	 * 获取配置文件的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		ClassLoader loader = PublicUtils.class.getClassLoader();
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

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
