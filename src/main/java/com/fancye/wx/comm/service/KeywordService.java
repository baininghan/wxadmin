/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import java.util.List;

import com.fancye.wx.comm.model.Keyword;

/** 
 * @author Fancye
 * @date 2018-3-23 下午1:33:05 
 *  
 */
public class KeywordService {

	public static KeywordService s = new KeywordService();
	
	public List<Keyword> list(String appId, String keyword) {
		return Keyword.dao.find("select * from wx_keyword where key_word = ? and app_id = ? and status = ?", keyword, appId, 1);
	}
}
