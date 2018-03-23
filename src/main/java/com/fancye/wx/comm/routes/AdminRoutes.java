/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.routes;

import com.fancye.wx.comm.controller.AdminController;
import com.fancye.wx.comm.controller.IndexController;
import com.jfinal.config.Routes;

/** 
 * @author Fancye
 * @date 2018-3-22 下午6:52:30 
 *  
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		setBaseViewPath("/WEB-INF/view");
		add("/", IndexController.class);
		add("/admin", AdminController.class,"/");
	}

}
