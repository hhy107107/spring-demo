package me.smallyellow.base.core.operator;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES 可逆加密算法
 * @author hhy
 * 2017年12月4日下午3:15:30
 */
public class AESOperator {
	private String sKey = "abcdef0123456789";  
	private String ivParameter = "0123456789abcdef";  
    private static AESOperator instance = null;  
  
    private AESOperator() {  
    }  
  
    public static AESOperator getInstance() {  
        if (instance == null)  
            instance = new AESOperator();  
        return instance;  
    }  
	
    /**
     * 加密
     * @param sSrc
     * @return
     */
	public String encrypt(String sSrc){  
	    String result = "";  
	    try {  
	        Cipher cipher;  
	        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	        byte[] raw = sKey.getBytes();  
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
	        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度  
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);  
	        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));  
	        byte[] resultBtye = new Base64().encode(encrypted);
	        result = new String(resultBtye);
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }   
        return result;  
    }  
	
	/**
	 * 解密
	 * @param sSrc
	 * @return
	 */
	public String decrypt(String sSrc){ 
		sSrc = sSrc.replaceAll(" ", "+"); //从浏览器传过来，会把加号变成空格，这里要改回来
	    try {  
	        byte[] raw = sKey.getBytes("ASCII");  
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());  
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);  
	        byte[] encrypted1 = new Base64().decode(sSrc);// 先用base64解密  
	        byte[] original = cipher.doFinal(encrypted1);  
	        String originalString = new String(original, "utf-8");  
	        return originalString;  
	    } catch (Exception ex) {  
	        ex.printStackTrace();  
	        return null;  
	    }  
	}  
	
//	public static void main(String[] args) {
//		String msg = "843847127@qq.com";
//		String jiami = AESOperator.getInstance().encrypt(msg);
//		System.out.println(jiami);
//		String jiemi = AESOperator.getInstance().decrypt(jiami);
//		System.out.println(jiemi);
//	}
}
