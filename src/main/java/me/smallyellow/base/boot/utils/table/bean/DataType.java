package me.smallyellow.base.boot.utils.table.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 数据字段类型对应实体类字段
 *
 */
public class DataType {
	
	public static String LONG = "Long";
	public static String STRING = "String";
	public static String SHORT = "Short";
	public static String INTEGER = "Integer";
	public static String DOUBLE = "Double";
	public static String BIGDECIMAL = "BigDecimal";
	public static String DATE = "Date";
	public static String ENUM = "enum";
	
    private static Map<String , String> map ;  
      
    static {  
        map = new HashMap<String , String>();
        map.put("binary", "byte[]");
        map.put("bit", "Short");
        map.put("bigint", "Long");
        map.put("varchar", "String");
        map.put("tinyint", "Short");
        map.put("int", "Integer");
        map.put("double", "Double");
        map.put("decimal", "BigDecimal");
        map.put("tinytext", "String");
        map.put("text", "String");
        map.put("mediumtext", "String");
        map.put("longtext", "String");
        map.put("datetime", "Date");
        map.put("date", "Date");
        map.put("enum", "String");
		map.put("DATETIME_IMPORT","import java.util.Date");
		map.put("DATE_IMPORT", "import java.util.Date");
		map.put("DECIMAL_IMPORT", "import java.math.BigDecimal");
    }
    
    public static String getImport(String dataType) {
    	 String tmp = dataType.toUpperCase();
		if (map.get(tmp)==null||"".equals(map.get(tmp))) {
		   return null;	
		}else{
		   return map.get(tmp);
		}
	}
      
    public static  String getPojoType(String dataType ) {  
        String tmp = dataType.toLowerCase();  
        StringTokenizer st = new StringTokenizer(tmp);  
        return map.get(st.nextToken()) ;  
    }

}
