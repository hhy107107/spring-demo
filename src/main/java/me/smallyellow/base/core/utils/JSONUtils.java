package me.smallyellow.base.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * json工具类
 * @author hhy
 * 2017年11月24日下午2:05:39
 */
public class JSONUtils {
	
	/**
	 * json string to bean
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(String json, Class<T> clazz) {
		Gson gson = new Gson();
		T bean = null;
		try {
			bean = gson.fromJson(json, clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
        return bean;
	}
}
