package me.smallyellow.base.boot.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import me.smallyellow.base.boot.utils.table.ModelTemplate;
import me.smallyellow.base.boot.utils.table.bean.DataType;
import me.smallyellow.base.boot.utils.table.bean.Table;
import me.smallyellow.base.boot.utils.table.bean.TableColumn;

/**
 * 数据库相关操作
 *
 */
public class TableUtils {
	
	/**
	 * 生产bean实体类
	 * @param url
	 * @param username
	 * @param password
	 * @param pakcageName
	 */
	public static void runToBean(String url, String username, String password, String pakcageName, String tableNameLike) {
		Connection conn;
		try {
			conn = getMySqlConnection(url, username, password);
			List<Table> tables = tableToModule(conn);
			for(Table t:tables) {
				if(isPermission(t.getName(), tableNameLike)) {
					tableToBean(pakcageName, t);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生产mybatis的Mapper和dao
	 * @param url
	 * @param username
	 * @param password
	 * @param pakcageName
	 */
	public static void runToMapper(String url, String username, String password, String pakcageName, String mapperPath, String tableNameLike) {
		Connection conn;
		try {
			conn = getMySqlConnection(url, username, password);
			List<Table> tables = tableToModule(conn);
			for(Table t:tables) {
				if(isPermission(t.getName(), tableNameLike)) {
					tableToDao(pakcageName, t);
					tableToMapper(pakcageName, mapperPath, t);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 表格转换成java实体类
	 * @param packageName
	 * @param table
	 */
	private static void tableToBean(String packageName, Table table) {
		String beanName = table.getBean();
		String tableComment = table.getComment();
		String modelName = ModelTemplate.dealLine(beanName, true);
		String tableName = table.getName();
		List<TableColumn> propertys = table.getColumns();
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		cfg.setClassForTemplateLoading(TableUtils.class,"/template");
		//得FreeMarker的关键对象———模板
		try {
			Template template = cfg.getTemplate("model.tmp.ftl");
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("modelName", modelName);
			rootMap.put("tableName", tableName);
			rootMap.put("pakageName", packageName);
			rootMap.put("modelCaption", tableComment);
			rootMap.put("idType", table.getIdType());
			rootMap.put("improtField", ModelTemplate.buildImprotField(propertys, packageName, true));
			rootMap.put("nameField", ModelTemplate.buildNameField(propertys));
			rootMap.put("strutField", ModelTemplate.buildStrutField(propertys, modelName));
			rootMap.put("getSetField", ModelTemplate.buildGetSetField(propertys));
			String javaPath = ModelTemplate.pkgToPathMaven(packageName+".model");
			ModelTemplate.buildTemplateFile(rootMap, javaPath + File.separator + modelName + ".java", template);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 表格转换成java实体类
	 * @param packageName
	 * @param table
	 */
	private static void tableToDao(String packageName, Table table) {
		String beanName = table.getBean();
		String modelName = ModelTemplate.dealLine(beanName, true);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		cfg.setClassForTemplateLoading(TableUtils.class,"/template");
		//得FreeMarker的关键对象———模板
		try {
			Template template = cfg.getTemplate("dao.tmp.ftl");
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("modelName", modelName);
			rootMap.put("pakageName", packageName);
			String javaPath = ModelTemplate.pkgToPathMaven(packageName+".mapper");
			ModelTemplate.buildTemplateFile(rootMap, javaPath + File.separator + modelName + "Mapper.java", template);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 表格转换成java实体类
	 * @param packageName
	 * @param table
	 */
	private static void tableToMapper(String packageName, String mapperPath, Table table) {
		String beanName = table.getBean();
		String modelName = ModelTemplate.dealLine(beanName, true);
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_21);
		cfg.setEncoding(Locale.getDefault(), "UTF-8");
		cfg.setClassForTemplateLoading(TableUtils.class,"/template");
		//得FreeMarker的关键对象———模板
		try {
			Template template = cfg.getTemplate("mapper.tmp.ftl");
			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("modelName", modelName);
			rootMap.put("pakageName", packageName);
			String javaPath = ModelTemplate.pkgToPathMavenResouce(mapperPath);
			ModelTemplate.buildTemplateFile(rootMap, javaPath + File.separator + modelName + "Mapper.xml", template);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获取Mysql数据库连接
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static Connection getMySqlConnection(String url, String username,
			String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = null;
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	/**
	 * 将数据库表结构转换成module对象
	 * @param conn
	 * @return
	 */
	private static List<Table> tableToModule(Connection conn) {
		List<Table> tables = new ArrayList<Table>();
		try {
			DatabaseMetaData databaseMetaData = conn.getMetaData();
			String[] tableType = { "TABLE" };
			ResultSet rs = databaseMetaData.getTables(null, null, "%", tableType);
			while (rs.next()) {
				Table table = new Table();
				String tableName = rs.getString("TABLE_NAME");
				String caption = rs.getString("REMARKS");
				table.setName(tableName);
				table.setComment(caption);
				table.setBean(ModelTemplate.dealLine(tableName, true));
				List<TableColumn> columns = buildTableColumn(databaseMetaData, tableName);
				table.setColumns(columns);
				for(TableColumn column:columns) {
					if("id".equalsIgnoreCase(column.getName())) {
						String idType = "Long";
						String idtypeTmp = DataType.getPojoType(column.getType());
						if("String".equals(idtypeTmp)) {
							idType = "String";
						} else if("Integer".equals(idtypeTmp)) {
							idType = "Integer";
						}
						table.setIdType(idType);
					}
				}
				tables.add(table);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tables;
	}

	/**
	 * 编辑表结构属性
	 */
	private static List<TableColumn> buildTableColumn(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
		List<TableColumn> columns = new ArrayList<TableColumn>();
		ResultSet colRet = databaseMetaData.getColumns(null, "%", tableName,"%");
		while(colRet.next()) { 
			String columnName = colRet.getString("COLUMN_NAME"); 
			String columnType = colRet.getString("TYPE_NAME"); 
			int datasize = colRet.getInt("COLUMN_SIZE"); 
			int digits = colRet.getInt("DECIMAL_DIGITS"); 
			int nullable = colRet.getInt("NULLABLE");
			String caption = colRet.getString("REMARKS");
			boolean notnull = true;
			if(nullable==1) {
				notnull = false;
			}
			TableColumn column = new TableColumn();
			column.setCaption(caption);
			column.setName(columnName);
			column.setProperty(ModelTemplate.dealName(columnName.toLowerCase()));
			column.setType(columnType);
			column.setDigits(digits);
			column.setLength(datasize);
			column.setNotnull(notnull);
			columns.add(column);
		}
		return columns;
	}
	
	/**
	 * 判断是否符合规则
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static boolean isPermission(String from, String to) {
		if (from == null || to == null) {
			return false;
		}
		if (to.endsWith("*")) {
			if (from.startsWith(to.substring(0, to.length() - 1))) {
				return true;
			}
		} else {
			if (from.equals(to)) {
				return true;
			}
		}
		return false;
	}

}
