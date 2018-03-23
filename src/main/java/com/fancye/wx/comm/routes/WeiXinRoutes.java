/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.routes;

import com.fancye.wx.comm.controller.WeixinApiController;
import com.fancye.wx.comm.controller.WeixinMsgController;
import com.jfinal.config.Routes;

/** 
 * @author Fancye
 * @date 2018-3-22 下午6:56:20 
 *  
 */
public class WeiXinRoutes extends Routes {

	@Override
	public void config() {
		add("/msg", WeixinMsgController.class);
		add("/api", WeixinApiController.class);
	}

}
