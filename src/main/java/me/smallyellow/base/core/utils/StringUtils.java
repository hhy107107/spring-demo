package me.smallyellow.base.core.utils;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * 字符串处理工具类
 */
public class StringUtils {

	public static final String SPACE = " ";
	public static final String DOT = ".";
	public static final String SLASH = "/";
	public static final String BACKSLASH = "\\";
	public static final String EMPTY = "";
	public static final String CRLF = "\r\n";
	public static final String NEWLINE = "\n";
	public static final String UNDERLINE = "_";
	public static final String COMMA = ",";

	public static final String HTML_NBSP = "&nbsp;";
	public static final String HTML_AMP = "&amp";
	public static final String HTML_QUOTE = "&quot;";
	public static final String HTML_LT = "&lt;";
	public static final String HTML_GT = "&gt;";

	public static final String EMPTY_JSON = "{}";

	/**
	 * 检查字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null) {
			return false;
		} else if (str.length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 当给定字符串为null时，转换为Empty
	 * 
	 * @param str
	 *            被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String nullToEmpty(String str) {
		return nullToDefault(str, EMPTY);
	}

	/**
	 * 如果字符串是<code>null</code>，则返回指定默认字符串，否则返回字符串本身。
	 * 
	 * <pre>
	 * nullToDefault(null, &quot;default&quot;)  = &quot;default&quot;
	 * nullToDefault(&quot;&quot;, &quot;default&quot;)    = &quot;&quot;
	 * nullToDefault(&quot;  &quot;, &quot;default&quot;)  = &quot;  &quot;
	 * nullToDefault(&quot;bat&quot;, &quot;default&quot;) = &quot;bat&quot;
	 * </pre>
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param defaultStr
	 *            默认字符串
	 * 
	 * @return 字符串本身或指定的默认字符串
	 */
	public static String nullToDefault(String str, String defaultStr) {
		return (str == null) ? defaultStr : str;
	}

	/**
	 * 当给定字符串为空字符串时，转换为<code>null</code>
	 * 
	 * @param str
	 *            被转换的字符串
	 * @return 转换后的字符串
	 */
	public static String emptyToNull(String str) {
		return isEmpty(str) ? null : str;
	}

	/**
	 * 把字符串按分隔符转换为数组
	 * 
	 * @param str
	 *            字符串
	 * @param expr
	 *            分隔符
	 * @return
	 */
	public static String[] stringToArray(String str, String expr) {
		return str.split(expr);
	}
	
	/**
	 * 把字符串按分隔符转换为List
	 * 
	 * @param str
	 *            字符串
	 * @param expr
	 *            分隔符
	 * @return
	 */
	public static List<Long> stringToListLong(String str, String expr) {
		String idsArray[] = StringUtils.stringToArray(str, expr);
		List<Long> ids = new ArrayList<Long>();
		for(String idStr:idsArray) {
			Long id = StringUtils.toLong(idStr);
			if(id!=null) {
				ids.add(id);
			}
		}
		return ids;
	}
	
	/**
	 * 把字符串按分隔符转换为List
	 * 
	 * @param str
	 *            字符串
	 * @param expr
	 *            分隔符
	 * @return
	 */
	public static List<String> stringToListString(String str, String expr) {
		String idsArray[] = StringUtils.stringToArray(str, expr);
		List<String> ids = new ArrayList<String>();
		for(String idStr:idsArray) {
			if(isNotEmpty(idStr)) {
				ids.add(idStr);
			}
		}
		return ids;
	}

	/**
	 * 将数组按照给定的分隔转化成字符串
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String arrayToString(String[] arr, String expr) {
		String strInfo = "";
		if (arr != null && arr.length > 0) {
			StringBuffer sf = new StringBuffer();
			for (String str : arr) {
				sf.append(str);
				sf.append(expr);
			}
			strInfo = sf.substring(0, sf.length() - 1);
		}
		return strInfo;
	}

	/**
	 * 将集合按照给定的分隔转化成字符串
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String listToString(List<String> list, String expr) {
		String strInfo = "";
		if (list != null && list.size() > 0) {
			StringBuffer sf = new StringBuffer();
			for (String str : list) {
				sf.append(str);
				sf.append(expr);
			}
			strInfo = sf.substring(0, sf.length() - 1);
		}
		return strInfo;
	}
	
	/**
	 * 将集合按照给定的分隔转化成字符串
	 * 
	 * @param arr
	 * @param expr
	 * @return
	 */
	public static String longListToString(List<Long> list, String expr) {
		String strInfo = "";
		if (list != null && list.size() > 0) {
			StringBuffer sf = new StringBuffer();
			for (Long str : list) {
				sf.append(str);
				sf.append(expr);
			}
			strInfo = sf.substring(0, sf.length() - 1);
		}
		return strInfo;
	}

	/**
	 * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * trim(null)          = null
	 * trim(&quot;&quot;)            = &quot;&quot;
	 * trim(&quot;     &quot;)       = &quot;&quot;
	 * trim(&quot;abc&quot;)         = &quot;abc&quot;
	 * trim(&quot;    abc    &quot;) = &quot;abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str) {
		return (null == str) ? null : trim(str, 0);
	}

	/**
	 * 给定字符串数组全部做去首尾空格
	 * 
	 * @param strs
	 *            字符串数组
	 */
	public static void trim(String[] strs) {
		if (null == strs) {
			return;
		}
		String str;
		for (int i = 0; i < strs.length; i++) {
			str = strs[i];
			if (null != str) {
				strs[i] = str.trim();
			}
		}
	}

	/**
	 * 除去字符串头部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * trimStart(null)         = null
	 * trimStart(&quot;&quot;)           = &quot;&quot;
	 * trimStart(&quot;abc&quot;)        = &quot;abc&quot;
	 * trimStart(&quot;  abc&quot;)      = &quot;abc&quot;
	 * trimStart(&quot;abc  &quot;)      = &quot;abc  &quot;
	 * trimStart(&quot; abc &quot;)      = &quot;abc &quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimStart(String str) {
		return trim(str, -1);
	}

	/**
	 * 除去字符串尾部的空白，如果字符串是<code>null</code>，则返回<code>null</code>。
	 * 
	 * <p>
	 * 注意，和<code>String.trim</code>不同，此方法使用<code>Character.isWhitespace</code>
	 * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
	 * 
	 * <pre>
	 * trimEnd(null)       = null
	 * trimEnd(&quot;&quot;)         = &quot;&quot;
	 * trimEnd(&quot;abc&quot;)      = &quot;abc&quot;
	 * trimEnd(&quot;  abc&quot;)    = &quot;  abc&quot;
	 * trimEnd(&quot;abc  &quot;)    = &quot;abc&quot;
	 * trimEnd(&quot; abc &quot;)    = &quot; abc&quot;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 *            要处理的字符串
	 * 
	 * @return 除去空白的字符串，如果原字串为<code>null</code>或结果字符串为<code>""</code>，则返回
	 *         <code>null</code>
	 */
	public static String trimEnd(String str) {
		return trim(str, 1);
	}

	/**
	 * 除去字符串头尾部的空白符，如果字符串是<code>null</code>，依然返回<code>null</code>。
	 * 
	 * @param str
	 *            要处理的字符串
	 * @param mode
	 *            <code>-1</code>表示trimStart，<code>0</code>表示trim全部，
	 *            <code>1</code>表示trimEnd
	 * 
	 * @return 除去指定字符后的的字符串，如果原字串为<code>null</code>，则返回<code>null</code>
	 */
	public static String trim(String str, int mode) {
		if (str == null) {
			return null;
		}

		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			while ((start < end) && (Character.isWhitespace(str.charAt(start)))) {
				start++;
			}
		}

		// 扫描字符串尾部
		if (mode >= 0) {
			while ((start < end) && (Character.isWhitespace(str.charAt(end - 1)))) {
				end--;
			}
		}

		if ((start > 0) || (end < length)) {
			return str.substring(start, end);
		}

		return str;
	}

	/**
	 * 是否以指定字符串开头
	 * 
	 * @param str
	 *            被监测字符串
	 * @param prefix
	 *            开头字符串
	 * @param isIgnoreCase
	 *            是否忽略大小写
	 * @return 是否以指定字符串开头
	 */
	public static boolean startWith(String str, String prefix, boolean isIgnoreCase) {
		if (isIgnoreCase) {
			return str.toLowerCase().startsWith(prefix.toLowerCase());
		} else {
			return str.startsWith(prefix);
		}
	}

	/**
	 * 是否以指定字符串结尾
	 * 
	 * @param str
	 *            被监测字符串
	 * @param suffix
	 *            结尾字符串
	 * @param isIgnoreCase
	 *            是否忽略大小写
	 * @return 是否以指定字符串结尾
	 */
	public static boolean endWith(String str, String suffix, boolean isIgnoreCase) {
		if (isIgnoreCase) {
			return str.toLowerCase().endsWith(suffix.toLowerCase());
		} else {
			return str.endsWith(suffix);
		}
	}

	/**
	 * 是否包含特定字符，忽略大小写，如果给定两个参数都为<code>null</code>，返回true
	 * 
	 * @param str
	 *            被检测字符串
	 * @param testStr
	 *            被测试是否包含的字符串
	 * @return 是否包含
	 */
	public static boolean containsIgnoreCase(String str, String testStr) {
		if (null == str) {
			// 如果被监测字符串和
			return null == testStr;
		}
		return str.toLowerCase().contains(testStr.toLowerCase());
	}

	/**
	 * 去掉指定前缀
	 * 
	 * @param str
	 *            字符串
	 * @param prefix
	 *            前缀
	 * @return 切掉后的字符串，若前缀不是 preffix， 返回原字符串
	 */
	public static String removePrefix(String str, String prefix) {
		if (isEmpty(str) || isEmpty(prefix)) {
			return str;
		}

		if (str.startsWith(prefix)) {
			return str.substring(prefix.length());
		}
		return str;
	}
	
	/**
	 * 忽略大小写去掉指定前缀
	 * 
	 * @param str 字符串
	 * @param prefix 前缀
	 * @return 切掉后的字符串，若前缀不是 prefix， 返回原字符串
	 */
	public static String removePrefixIgnoreCase(String str, String prefix) {
		if(isEmpty(str) || isEmpty(prefix)){
			return str;
		}
		
		if (str.toLowerCase().startsWith(prefix.toLowerCase())) {
			return str.substring(prefix.length());
		}
		return str;
	}

	/**
	 * 去掉指定后缀
	 * 
	 * @param str 字符串
	 * @param suffix 后缀
	 * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
	 */
	public static String removeSuffix(String str, String suffix) {
		if(isEmpty(str) || isEmpty(suffix)){
			return str;
		}
		
		if (str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}

	/**
	 * 忽略大小写去掉指定后缀
	 * 
	 * @param str 字符串
	 * @param suffix 后缀
	 * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
	 */
	public static String removeSuffixIgnoreCase(String str, String suffix) {
		if(isEmpty(str) || isEmpty(suffix)){
			return str;
		}
		
		if (str.toLowerCase().endsWith(suffix.toLowerCase())) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}
	
	/**
	 * 转换为int<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Integer toInt(Object value, Integer defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Integer) {
			return (Integer)value;
		}
		if(value instanceof Number){
			return ((Number) value).intValue();
		}
		final String valueStr = value==null?null:value.toString();
		if (isEmpty(valueStr)){
			return defaultValue;
		}
		try {
			return Integer.parseInt(valueStr.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换为int<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Integer toInt(Object value) {
		return toInt(value, null);
	}
	
	/**
	 * 转换为long<br>
	 * 如果给定的值为空，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static Long toLong(Object value, Long defaultValue) {
		if (value == null){
			return defaultValue;
		}
		if(value instanceof Long) {
			return (Long)value;
		}
		if(value instanceof Number){
			return ((Number) value).longValue();
		}
		final String valueStr = value==null?null:value.toString();
		if (isEmpty(valueStr)){
			return defaultValue;
		}
		try {
			//支持科学计数法
			return new BigDecimal(valueStr.trim()).longValue();
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换为long<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static Long toLong(Object value) {
		return toLong(value, null);
	}
	
	/**
	 * 将对象转为字符串<br>
	 * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
	 * 2、对象数组会调用Arrays.toString方法
	 * 
	 * @param obj 对象
	 * @param charsetName 字符集
	 * @return 字符串
	 */
	public static String str(Object obj, String charsetName){
		return str(obj, Charset.forName(charsetName));
	}
	
	/**
	 * 将对象转为字符串<br>
	 * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
	 * 2、对象数组会调用Arrays.toString方法
	 * 
	 * @param obj 对象
	 * @param charset 字符集
	 * @return 字符串
	 */
	public static String str(Object obj, Charset charset){
		if(null == obj){
			return null;
		}
		
		if(obj instanceof String) {
			return (String)obj;
		}else if(obj instanceof byte[] || obj instanceof Byte[]){
			return str((Byte[])obj, charset);
		}else if(obj instanceof ByteBuffer){
			return str((ByteBuffer)obj, charset);
		}else if(CollectionUtils.isArray(obj)){
			return CollectionUtils.toString(obj);
		}
		
		return obj.toString();
	}
	
	/**
	 * 改进JDK subString<br>
	 * index从0开始计算，最后一个字符为-1<br>
	 * 如果from和to位置一样，返回 "" example: abcdefgh 2 3 -> c abcdefgh 2 -3 -> cde
	 * 
	 * @param string String
	 * @param fromIndex 开始的index（包括）
	 * @param toIndex 结束的index（不包括）
	 * @return 字串
	 */
	public static String sub(String string, int fromIndex, int toIndex) {
		int len = string.length();

		if (fromIndex < 0) {
			fromIndex = len + fromIndex;

			if (toIndex == 0) {
				toIndex = len;
			}
		}

		if (toIndex < 0) {
			toIndex = len + toIndex;
		}

		if (toIndex < fromIndex) {
			int tmp = fromIndex;
			fromIndex = toIndex;
			toIndex = tmp;
		}

		if (fromIndex == toIndex) {
			return EMPTY;
		}

		char[] strArray = string.toCharArray();
		char[] newStrArray = Arrays.copyOfRange(strArray, fromIndex, toIndex);
		return new String(newStrArray);
	}

	/**
	 * 切割前部分
	 * 
	 * @param string 字符串
	 * @param toIndex 切割到的位置（不包括）
	 * @return 切割后的字符串
	 */
	public static String subPre(String string, int toIndex) {
		return sub(string, 0, toIndex);
	}

	/**
	 * 切割后部分
	 * 
	 * @param string 字符串
	 * @param fromIndex 切割开始的位置（包括）
	 * @return 切割后的字符串
	 */
	public static String subSuf(String string, int fromIndex) {
		if (isEmpty(string)) {
			return null;
		}
		return sub(string, fromIndex, string.length());
	}
	
	/**
	 * 格式化文本, {} 表示占位符<br>
	 * 例如：format("aaa {} ccc", "bbb")   ---->    aaa bbb ccc
	 * 
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param values 参数值
	 * @return 格式化后的文本
	 */
	public static String format(String template, Object... values) {
		if (CollectionUtils.isEmpty(values) || isEmpty(template)) {
			return template;
		}

		final StringBuilder sb = new StringBuilder();
		final int length = template.length();

		int valueIndex = 0;
		char currentChar;
		for (int i = 0; i < length; i++) {
			if (valueIndex >= values.length) {
				sb.append(sub(template, i, length));
				break;
			}

			currentChar = template.charAt(i);
			if (currentChar == '{') {
				final char nextChar = template.charAt(++i);
				if (nextChar == '}') {
					sb.append(str(values[valueIndex++], CharsetUtils.CHARSET_UTF_8));
				} else {
					sb.append('{').append(nextChar);
				}
			} else {
				sb.append(currentChar);
			}

		}

		return sb.toString();
	}

	/**
	 * 格式化文本，使用 {varName} 占位<br>
	 * map = {a: "aValue", b: "bValue"}
	 * format("{a} and {b}", map)    ---->    aValue and bValue
	 * 
	 * @param template 文本模板，被替换的部分用 {key} 表示
	 * @param map 参数值对
	 * @return 格式化后的文本
	 */
	public static String format(String template, Map<?, ?> map) {
		if (null == map || map.isEmpty()) {
			return template;
		}

		for (Entry<?, ?> entry : map.entrySet()) {
			template = template.replace("{" + entry.getKey() + "}", entry.getValue().toString());
		}
		return template;
	}

	
	/**
	 * 转换为字符串<br>
	 * 如果给定的值为null，或者转换失败，返回默认值<br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @param defaultValue 转换错误时的默认值
	 * @return 结果
	 */
	public static String objectToStr(Object value, String defaultValue) {
		if(null == value) {
			return defaultValue;
		}
		if(value instanceof String) {
			return (String)value;
		}else if(CollectionUtils.isArray(value)){
			return CollectionUtils.toString(value);
		}
		return value.toString();
	}
	
	/**
	 * 转换为字符串<br>
	 * 如果给定的值为<code>null</code>，或者转换失败，返回默认值<code>null</code><br>
	 * 转换失败不会报错
	 * 
	 * @param value 被转换的值
	 * @return 结果
	 */
	public static String objectToStr(Object value) {
		return objectToStr(value, null);
	}

    /**
     * 随机生成字符串
     * @param length 表示生成字符串的长度  
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();     
     } 
}
