/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.controller;

import com.fancye.wx.comm.interceptor.ConfigInterceptor;
import com.fancye.wx.comm.service.ConfigService;
import com.fancye.wx.comm.utils.RedPackUtil;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/** 
 * @author Fancye
 * @date 2018-3-23 下午2:52:53 
 *  
 */
@Before(ConfigInterceptor.class)
public class RedPackApiController extends Controller {

	public void sendredpack() {
		String rmid = getPara("rmid");
		String openId = getPara("openId");
		String ip = getPara("ip");
		RedPackUtil.sendRedPack(ConfigService.s.getByRmid(rmid), 1, openId, ip);
		
		renderNull();
	}
}
