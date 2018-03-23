/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;

/** 
 * @author Fancye
 * @date 2018-3-23 下午12:12:10 
 *  
 */
public class WxEventService {

	public static WxEventService s = new WxEventService();
	Log log = Log.getLog(WxEventService.class);
	
	public void followEvent() {
		
	}
	
	/**
	 * 文本消息事件
	 * @param config
	 * @param inMsg
	 */
	public void inText(String rmid, InTextMsg inTextMsg) {
		
	}
	
	
}
