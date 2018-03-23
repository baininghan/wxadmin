/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import java.util.List;

import com.fancye.wx.comm.model.Material;

/** 
 * @author Fancye
 * @date 2018-3-23 下午2:07:07 
 *  
 */
public class MaterialService {

	public static MaterialService s = new MaterialService();

	public List<Material> getMaterialBySKI(String appId, String article_key) {
		return Material.dao.find("select * from wx_material where app_id = ? and keyword = ? and status = ?", appId, article_key, 1);
	}
	
	
}
