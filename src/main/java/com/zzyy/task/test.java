package com.zzyy.task;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class test {
	
	//用户名
	private static String Uid = "zhouyu0320";
	
	//接口安全秘钥
	private static String Key = "5479cba138884be3bae7";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "18273013312";
	
	//短信内容
	private static String smsText = "抱歉，该日期入园名额不足！";
	
	
	public static void main(String[] args) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
		}else{
			System.out.println(client.getErrorMsg(result));
		}
	}
}
