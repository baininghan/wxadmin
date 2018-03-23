/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.interceptor;

import com.fancye.wx.comm.model.Config;
import com.fancye.wx.comm.service.ConfigService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

/**
 * 1. 检查rmid是否为空：rmid-随机数字，用于区分不同的公众号请求来源
 * 2. 将公众号配置信息加入工程上下文环境
 * @author Fancye
 * @date 2018-3-23 下午2:56:59 
 *  
 */
public class ConfigInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		String rmid = c.getPara("rmid");
		if(StrKit.isBlank(rmid)) {
			throw new IllegalArgumentException("rmid 值不能为空");
		}
		
		Config config = ConfigService.s.getByRmid(rmid);
		c.getSession().setAttribute("config", config);
		if(config == null) {
			throw new IllegalArgumentException("rmid："+rmid+" 值不能用，请联系管理员");
		}
		ConfigService.s.getApiConfig(config);
		inv.invoke();
	}

}
