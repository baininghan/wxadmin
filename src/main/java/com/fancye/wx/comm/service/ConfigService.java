/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.service;

import com.fancye.wx.comm.model.Config;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/** 
 * @author Fancye
 * @date 2018-3-23 下午1:39:23 
 *  
 */
public class ConfigService {

	public static ConfigService s = new ConfigService();
	
	public Config getByRmid(String rmid) {
		return Config.dao.findFirst("select * from wx_config where rmid = ? and status = ?", rmid, 1);
	}
	
	/**
	 * WeiXinMsgController.ConfigInterceptor 会使用到
	 * @param config
	 */
	public void getApiConfig(Config config) {
		ApiConfig ac = new ApiConfig();
		
		// 配置微信 API 相关常量
		ac.setToken(config.getToken());
		ac.setAppId(config.getAppId());
		ac.setAppSecret(config.getAppSecret());
		
		/**
		 *  是否对消息进行加密，对应于微信平台的消息加解密方式：
		 *  1：true进行加密且必须配置 encodingAesKey
		 *  2：false采用明文模式，同时也支持混合模式
		 */
		ac.setEncryptMessage(config.getEncrypt() == 1 ? true:false);
		ac.setEncodingAesKey(config.getEncryptKey());
		ApiConfigKit.putApiConfig(ac);
	}
}
