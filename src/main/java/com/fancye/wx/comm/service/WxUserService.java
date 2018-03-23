/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fancye.wx.comm.kit.URLCodeKit;
import com.fancye.wx.comm.model.User;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;

/** 
 * @author Fancye
 * @date 2018-3-23 下午12:00:45 
 *  
 */
public class WxUserService {

	public User save(String appId, ApiResult apiResult) {
		JSONObject jsonObject = JSON.parseObject(apiResult.getJson());
		String openId = jsonObject.getString("openid");
		String nickName = jsonObject.getString("nickname");
		nickName = URLCodeKit.encode(nickName);
		int sex = jsonObject.getIntValue("sex");
		String city = jsonObject.getString("city");// 城市
		String province = jsonObject.getString("province");// 省份
		String country = jsonObject.getString("country");// 国家
		String headimgurl = jsonObject.getString("headimgurl");
		String unionid = jsonObject.getString("unionid");

		User user = getUserByOpenId(appId,openId);
		if(user != null) {
			user.setNickName(nickName);
			user.setUnionid(unionid);
			user.setHeadimgurl(headimgurl);
			user.setCountry(country);
			user.setCity(city);
			user.setProvince(province);
			user.setSex(sex);
			user.setUpdateTime(new Date());
			boolean update = user.update();
			if (update) {
				return user;
			}
		} else {
			if(StrKit.notBlank(openId)) {
				User me = new User();
				me.setAppId(appId);
				me.setOpenId(openId);
				me.setNickName(nickName);
				me.setUnionid(unionid);
				me.setHeadimgurl(headimgurl);
				me.setCountry(country);
				me.setCity(city);
				me.setProvince(province);
				me.setSex(sex);
				me.setUpdateTime(new Date());
				me.setCreateTime(new Date());
				boolean save = me.save();
				if (save) {
					return me;
				}
				
			}
		}
		
		return null;
	}
	
	public User getUserByOpenId(String appId, String openId) {
		return User.dao.findFirst("select * from wx_user where app_id = ? and open_id = ?", appId, openId);
	}
}
