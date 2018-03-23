/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.controller;

import com.jfinal.core.Controller;

/** 
 * @author Fancye
 * @date 2018-3-22 下午6:54:32 
 *  
 */
public class AdminController extends Controller {

	public void index() {
		renderText("后台Admin管理页面.");
	}
}
