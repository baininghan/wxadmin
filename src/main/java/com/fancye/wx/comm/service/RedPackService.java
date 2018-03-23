/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import com.fancye.wx.comm.model.Redpack;

/** 
 * @author Fancye
 * @date 2018-3-23 下午2:14:41 
 *  
 */
public class RedPackService {

	public static final RedPackService s = new RedPackService();
	
	public Redpack getRedPackById(int id){
		return Redpack.dao.findFirst("select * from wx_redpack where id = ? and status = ?", id, 1);
	}
}
