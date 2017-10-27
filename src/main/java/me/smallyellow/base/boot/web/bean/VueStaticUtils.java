package me.smallyellow.base.boot.web.bean;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import me.smallyellow.base.boot.utils.mapper.JsonMapper;


public class VueStaticUtils {

	public static JsonMapper jsonMapper= JsonMapper.nonEmptyMapper();
	
	public static void init(String url, final VueStaticInfo vue) {
		VueStaticInfo newVue = VueStaticUtils.getVueStaticInfo(url);
		if(newVue!=null) {
			vue.setAppCss(newVue.getAppCss());
			vue.setAppJS(newVue.getAppJS());
			vue.setManifestJs(newVue.getManifestJs());
			vue.setVendorJs(newVue.getVendorJs());
			vue.setInit(true);
		}
	}
	
	/**
	 * 获取静态资源文件
	 */
	private static VueStaticInfo getVueStaticInfo(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		try {
			CloseableHttpResponse response = client.execute(httpget);
			try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                if (entity != null) { 
                	String resJson = EntityUtils.toString(entity);
                	return jsonMapper.fromJson(resJson, VueStaticInfo.class);
                }
            } finally {  
                response.close();  
            }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
