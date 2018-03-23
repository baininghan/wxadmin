/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com;

import com.jfinal.core.JFinal;

/** 
 * @author Fancye
 * @date 2018-3-22 下午6:11:06 
 *  
 */
public class AppRun {

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 0);// 启动配置项
	}
}
