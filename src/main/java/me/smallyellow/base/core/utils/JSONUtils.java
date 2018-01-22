package me.smallyellow.base.core.utils;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

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
	
	/**
	 * json string to list
	 * @param json
	 * @param clazzList
	 * @return
	 */
	public static <T> List<T> toList(String json, List<T> clazzList) {
		Gson gson = new Gson();
		List<T> retList = gson.fromJson(json, new TypeToken<List<T>>() {}.getType());
		return retList;
	}
	
	/**
	 * bean to json string
	 * @param bean
	 * @return
	 */
	public static String toJSON(Object bean) {
		Gson gson = new Gson();
		return gson.toJson(bean);
	}
}
