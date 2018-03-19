package me.smallyellow.hhy;

import java.util.HashMap;
import java.util.Map;

public class T {

	public static void main(String[] args) {
		int i = 12;
		//System.out.println(digitToChnNum(i));
		String b = "二十三";//十一  二十一 三十
		System.out.println(chnNumToDigit(b));
	}
	
	/**
	 * 中文转数字
	 * @param chnNum
	 * @return
	 */
	public static int chnNumToDigit(String chnNum) {
		Map<String,Integer> numMap = new HashMap<String,Integer>();
		int[] digits = {0,1,2,3,4,5,6,7,8,9};
		String[] nums = {"零","一","二","三","四","五","六","七","八","九"};
		for(int i = 0 ;i < digits.length ; i++){
			numMap.put(nums[i], digits[i]);
		}
		char[] chars = chnNum.toCharArray();
		String first = String.valueOf(chars[0]);
		if (chnNum.equals("十")) {
			return 10;
		} else if(chnNum.length() == 1) {
			return numMap.get(chnNum);
		} else if(chnNum.length() == 2) {
			String last = String.valueOf(chars[1]);
			if(first.equals("十")) {
				return 10 + numMap.get(last).intValue();
			} else if(!first.equals("零") && last.equals("十")) {
				return Integer.parseInt(numMap.get(first).toString() + "0");
			}
		} else if(chnNum.length() == 3) {
			String last = String.valueOf(chars[2]);
			String result = numMap.get(first).toString() + numMap.get(last).toString();
			return Integer.parseInt(result);
		} else {
			return -1;
		}
		return 0;
	}
	
	/**
	 * 0~99 阿拉伯数字转中文数字
	 * @param digit 要转的数字
	 * @return
	 */
	public static String digitToChnNum(int digit){
		Map<Integer,String> numMap = new HashMap<Integer,String>();
		int[] digits = {0,1,2,3,4,5,6,7,8,9};
		String[] nums = {"零","一","二","三","四","五","六","七","八","九"};
		for(int i = 0 ;i < digits.length ; i++){
			numMap.put(digits[i], nums[i]);
		}
        if(digit < 10 && digit >= 0){
        	return numMap.get(digit);
        }else if(digit >= 10 && digit < 100){
        	int lastNum = digit % 10;
        	int firstNum = digit / 10;
        	if(firstNum == 1){
        		if(lastNum == 0){
        			return "十";
        		}else{
        			return "十"+numMap.get(lastNum);
        		}
        	}else{
        		if(lastNum == 0){
        			return numMap.get(firstNum)+"十";
        		}else{
        			return numMap.get(firstNum)+"十"+numMap.get(lastNum);
        		}
        	}
        }
		return "不符合规定的数字 [0~99]";
	}
}
