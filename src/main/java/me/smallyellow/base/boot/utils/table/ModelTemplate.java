package me.smallyellow.base.boot.utils.table;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Template;
import me.smallyellow.base.boot.utils.table.bean.DataType;
import me.smallyellow.base.boot.utils.table.bean.TableColumn;
import me.smallyellow.base.core.utils.FilenameUtils;



/**
 * 注解相关工具类
 *
 */
public class ModelTemplate {
	
	public static final String LINE = "\r\n";
	public static final String TAB = "\t";
	
	/**
	 * 生成java文件
	 * @param filePath
	 * @param fileContent
	 */
	public static void buildTemplateFile(Map<String, Object> rootMap, String realFileName, Template template) {
		//如果已經存在不创建
		File realFile = new File(realFileName);
		if (!realFile.exists()) {
			try {
				Writer out = new OutputStreamWriter(new FileOutputStream(realFileName), "UTF-8");
				template.process(rootMap, out);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 构建包内容
	 * @param propertys
	 */
	public static String buildImprotField(List<TableColumn> propertys, String modelPackage, boolean base) {
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = new HashMap<String, String>();
		for (TableColumn property:propertys) {
			String im = DataType.getImport(property.getType()+ "_IMPORT");
			if (im != null && map.get(im) == null) {
				map.put(im, im);
				sb.append(LINE);
				sb.append(im + ";");
			}
		}
		return sb.toString();
	}

	/**
	 * 构建相关属性
	 * @param propertys
	 * @return
	 */
	public static String buildNameField(List<TableColumn> propertys) {
		StringBuffer sb = new StringBuffer();
		for (TableColumn property:propertys) {
			String column = property.getName();
			String prop = property.getProperty();
			String columntype = property.getType();
			String proptype = DataType.getPojoType(columntype);
			String caption = property.getCaption();
			boolean notnull = property.isNotnull();
			// 如果是id自增主键的话
			if(!"id".equalsIgnoreCase(column)) {
				sb.append(TAB);
				sb.append("@Column(name = \""+column+"\")");
				sb.append(LINE);
				sb.append(TAB);
				sb.append("private ");
				sb.append(proptype+" "+prop+";");
				sb.append("//");
				sb.append(" "+caption);
				if(notnull) {
					sb.append("（非空） ");
				}
				sb.append(LINE);
			}
		}
		return sb.toString();
	}

	public static Object buildStrutField(List<TableColumn> propertys, String beanName) {
		StringBuffer sb = new StringBuffer();
		sb.append(TAB).append("public "+beanName+"() {}");
		StringBuffer strut = new StringBuffer();
		StringBuffer strutBody = new StringBuffer();
		StringBuffer notstrut = new StringBuffer();
		StringBuffer notstrutBody = new StringBuffer();
		for (int j = 0; j < propertys.size(); j++) {
			TableColumn property = propertys.get(j);
			boolean notnull = property.isNotnull();
			String pojoType = DataType.getPojoType(property.getType());
			String name = property.getProperty();
			if(notnull) {
				if(j!=0) {
					notstrut.append(",");
				}
				notstrut.append(pojoType+" "+name);
				notstrutBody.append(LINE).append(TAB).append(TAB).append("this."+name+"="+name+";");
			}
			if(j!=0) {
				strut.append(",");
			}
			strut.append(pojoType+" "+name);
			strutBody.append(LINE).append(TAB).append(TAB).append("this."+name+"="+name+";");
		}
		
		sb.append(LINE).append(TAB).append("public "+beanName+"("+strut+") {");
		sb.append(strutBody);
		sb.append(LINE).append(TAB).append("}");
		sb.append(LINE).append(TAB).append("public "+beanName+" setNotNull("+notstrut+") {");
		sb.append(notstrutBody);
		sb.append(LINE).append(TAB).append(TAB).append("return this;");
		sb.append(LINE).append(TAB).append("}");
		return sb.toString();
	}

	public static String buildGetSetField(List<TableColumn> propertys) {
		StringBuffer sb = new StringBuffer();
		for (TableColumn property:propertys) {
			if(!"id".equalsIgnoreCase(property.getName())) {
				String pojoType = DataType.getPojoType(property.getType());
				String column = property.getProperty();
				sb.append(buildgetSetStr(column, pojoType));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 构建get set函数
	 * @param column
	 * @param pojoType
	 * @return
	 */
	public static String buildgetSetStr(String column, String pojoType) {
		StringBuffer sb = new StringBuffer();
		String columnName = ModelTemplate.dealLine(column, false);
		String methName = ModelTemplate.dealLine(column, true);
		String getName = null;
		String setName = null;
		if (column.length() > 1) {
			getName = "public " + pojoType + " get"
					+ methName + "() {";
			setName = "public void set"
					+ methName + "("
					+ pojoType + " " + columnName + ") {";
		} else {
			getName = "public get" + column.toUpperCase() + "() {";
			setName = "public set" + column.toUpperCase() + "("
					+ pojoType + " " + column + ") {";
		}
		sb.append(TAB).append(getName);
		sb.append(LINE).append(TAB).append(TAB);
		sb.append("return " + columnName + ";");
		sb.append(LINE).append(TAB).append("}");
		sb.append(LINE).append(TAB).append(setName);
		sb.append(LINE).append(TAB).append(TAB);
		sb.append("this." + columnName + " = " + columnName + ";");
		sb.append(LINE).append(TAB).append("}");
		sb.append(LINE);
		return sb.toString();
	}
	
	/**
	 * 把下划线后一位的字母变大写
	 * @param tableName
	 * @return
	 */
	public static String dealLine(String tableName, boolean isUpperFirst) {
		if(isUpperFirst) {
			tableName = tableName.substring(0, 1).toUpperCase() + tableName.subSequence(1, tableName.length());
		}
		// 处理下划线情况，把下划线后一位的字母变大写；
		tableName = dealName(tableName);
		return tableName;
	}
	
	/**
	 * 下划线后一位字母大写
	 * @param tableName
	 * @return
	 */
	public static String dealName(String columnName) {
		if (columnName.contains("_")) {
			StringBuffer names = new StringBuffer();
			String arrayName[] = columnName.split("_");
			names.append(arrayName[0]);
			for (int i = 1; i < arrayName.length; i++) {
				String arri = arrayName[i];
				String tmp = arri.substring(0, 1).toUpperCase()
						+ arri.substring(1, arri.length());
				names.append(tmp);
			}
			columnName = names.toString();
		}
		return columnName;
	}
	
	/**
	 * 包名转换成实际路径
	 * @param packagesName
	 * @return
	 */
	public static String pkgToPathMaven(String packagesName) {
		String path = System.getProperty("user.dir")
			+ FilenameUtils.separatorsToSystem("\\src\\main\\java\\")
			+ pkgToPath(packagesName);
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	
	/**
	 * 包名转换成实际路径
	 * @param packagesName
	 * @return
	 */
	public static String pkgToPathMavenResouce(String packagesName) {
		String path = System.getProperty("user.dir")
			+ FilenameUtils.separatorsToSystem("\\src\\main\\resources\\")
			+ pkgToPath(packagesName);
		File file = new File(path);
		if(!file.exists()) {
			file.mkdirs();
		}
		return path;
	}
	
	/**
	 * 包名转换成实际路径
	 * @param packagesName
	 * @return
	 */
	public static String pkgToPath(String packagesName) {
		return packagesName.replace(".", File.separator);
	}

}
