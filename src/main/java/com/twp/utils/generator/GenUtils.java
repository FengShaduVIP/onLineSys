package com.twp.utils.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.twp.utils.DateUtils;
import com.twp.utils.RRException;

/**
 * 代码生成器 工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("template/Entity.java.vm");
		templates.add("template/Dao.java.vm");
		templates.add("template/Dao.xml.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		templates.add("template/list.html.vm");
		templates.add("template/list.js.vm");
		templates.add("template/add.html.vm");
		templates.add("template/add.js.vm");
		return templates;
	}

	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip) {
		// 配置信息
		Configuration config = getConfig();

		VelocityContext context = new VelocityContext(tableInfo(table, columns));

		// 获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);

			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(
						getFileName(template, table.get("tableName"),
								config.getString("package"))));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new RRException(
						"渲染模板失败，表名：" + table.get("tableName"), e);
			}
		}
	}

	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' })
				.replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RRException("获取配置文件失败，", e);
		}
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className,
			String packageName) {
		String packagePath = "";
		if (StringUtils.isNotBlank(packageName)) {
			packagePath = packageName.replace(".", File.separator)
					+ File.separator;
		}

		if (template.contains("Entity.java.vm")) {
			return packagePath + "entity" + File.separator + className
					+ "Entity.java";
		}

		if (template.contains("Dao.java.vm")) {
			return packagePath + "dao" + File.separator + className
					+ "Dao.java";
		}

		if (template.contains("Dao.xml.vm")) {
			return packagePath + "dao" + File.separator + className + "Dao.xml";
		}

		if (template.contains("Service.java.vm")) {
			return packagePath + "service" + File.separator + className
					+ "Service.java";
		}

		if (template.contains("ServiceImpl.java.vm")) {
			return packagePath + "service" + File.separator + "impl"
					+ File.separator + className + "ServiceImpl.java";
		}

		if (template.contains("Controller.java.vm")) {
			return packagePath + "controller" + File.separator + className
					+ "Controller.java";
		}

		if (template.contains("list.html.vm")) {
			return "html" + File.separator + className.toLowerCase() + ".html";
		}

		if (template.contains("list.js.vm")) {
			return "js" + File.separator + className.toLowerCase() + ".js";
		}

		if (template.contains("add.html.vm")) {
			return "html" + File.separator + className.toLowerCase()
					+ "_add.html";
		}

		if (template.contains("add.js.vm")) {
			return "js" + File.separator + className.toLowerCase() + "_add.js";
		}

		return null;
	}

	/**
	 * 创建文件
	 * 
	 * @param table
	 * @param columns
	 */
	public static void createCode(Map<String, String> table,List<Map<String, String>> columns) {
		Configuration config = getConfig();
		VelocityContext context = new VelocityContext(tableInfo(table, columns));
		String className = tableToJava(table.get("tableName"),
				config.getString("tablePrefix"));
		// 获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			// 渲染模板
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			try {
				String filePath = fileAbsolutePath(template, className,
						config.getString("package"));
				File file = new File(filePath);
				if (file.exists())
					file.delete();
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				FileOutputStream stream = new FileOutputStream(filePath);
				OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
				tpl.merge(context, writer);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				throw new RRException("渲染模板失败，表名：" + table.get("tableName"), e);
			}
		}
	}
	/**
	 * 返回生成文件的绝对路径地址
	 * @param template
	 * @param className
	 * @param packageName
	 * @return
	 */
	public static String fileAbsolutePath(String template, String className,
			String packageName) {
		// 配置信息
		Configuration config = getConfig();

		String xmlPath = config.getString("XML_PATH") + File.separator;
		String workSpacePath = config.getString("WORKSPACE_PATH")
				+ File.separator;
		String htmlPath = config.getString("HTML_PATH") + File.separator
				+ className.toLowerCase() + File.separator;
		String jsPath = config.getString("JS_PATH") + File.separator
				+ className.toLowerCase() + File.separator;
		String packagePath = changePath(packageName);
		String srcPath = "src" + File.separator + "main" + File.separator
				+ "java" + File.separator;

		if (template.contains("Entity.java.vm")) {
			return workSpacePath + srcPath + packagePath + "entity"
					+ File.separator + className + "Entity.java";
		}

		if (template.contains("Dao.java.vm")) {
			return workSpacePath + srcPath + packagePath + "dao"
					+ File.separator + className + "Dao.java";
		}

		if (template.contains("Dao.xml.vm")) {
			return workSpacePath + xmlPath + className + "Dao.xml";
		}

		if (template.contains("Service.java.vm")) {
			return workSpacePath + srcPath + packagePath + "service"
					+ File.separator + className + "Service.java";
		}

		if (template.contains("ServiceImpl.java.vm")) {
			return workSpacePath + srcPath + packagePath + "service"
					+ File.separator + "impl" + File.separator + className
					+ "ServiceImpl.java";
		}

		if (template.contains("Controller.java.vm")) {
			return workSpacePath + srcPath + packagePath + "controller"
					+ File.separator + className + "Controller.java";
		}

		if (template.contains("list.html.vm")) {
			return workSpacePath + htmlPath + className.toLowerCase() + ".html";
		}

		if (template.contains("list.js.vm")) {
			return workSpacePath + jsPath + className.toLowerCase() + ".js";
		}

		if (template.contains("add.html.vm")) {
			return workSpacePath + htmlPath + className.toLowerCase()
					+ "_add.html";
		}

		if (template.contains("add.js.vm")) {
			return workSpacePath + jsPath + className.toLowerCase() + "_add.js";
		}

		return null;
	}

	public static String changePath(String str) {
		if (StringUtils.isNotBlank(str)) {
			str = str.replace(".", File.separator) + File.separator;
		}
		return str;
	}

	public static Map<String, Object> tableInfo(Map<String, String> table,
			List<Map<String, String>> columns) {
		Configuration config = getConfig();
		// 表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		// 表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(),
				config.getString("tablePrefix"));
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));

		// 列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));

			// 列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

			// 列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(),
					"unknowType");
			columnEntity.setAttrType(attrType);

			// 是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey"))
					&& tableEntity.getPk() != null) {
				tableEntity.setPk(columnEntity);
			}

			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);

		// 没主键，则第一个字段为主键
		if (tableEntity.getPk() == null) {
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}

		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);

		// 封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.getString("package"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime",
				DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));

		return map;
	}
}
