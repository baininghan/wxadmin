/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.utils;

import java.util.Map;

import com.fancye.wx.comm.api.hb.PayModel;
import com.fancye.wx.comm.api.hb.ReadHbModle;
import com.fancye.wx.comm.api.hb.RedHbApi;
import com.fancye.wx.comm.model.Config;
import com.fancye.wx.comm.model.Redpack;
import com.fancye.wx.comm.service.RedPackService;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.kit.PaymentKit;

/** 
 * 微信红包工具类
 * @author Fancye
 * @date 2018-3-23 下午1:53:56 
 *  
 */
public class RedPackUtil {
	static Log logger = Log.getLog(RedPackUtil.class);

	/**
	 * 发送红包
	 * @param config 公众号配置表
	 * @param readpackId 红包配置表的Id
	 * @param openId 
	 * @param ip 
	 * @return
	 */
	public static boolean sendRedPack(Config config, int readpackId, String openId, String ip){
		Redpack redpack = RedPackService.s.getRedPackById(readpackId);
		if (null != redpack) {
			String ns = String.valueOf(System.currentTimeMillis());
			Map<String, String> params = ReadHbModle.Builder()
					.setPayModel(PayModel.BUSINESSMODEL)
					.setNonceStr(ns)// 有默认值可不设置
					.setMchBillNo(ns)// 有默认值可不设置
					.setMchId(config.getMchId())
					.setWxAppId(config.getAppId())
					.setSendName(redpack.getSendName())
					.setReOpenId(openId)// 根据实际的值修改
					.setTotalAmount(redpack.getTotalAmount())// 单位分 红包的金额必须在1.00元到200.00元之间
					.setTotalNum(redpack.getTotalNum())
					.setWishing(redpack.getWishing())
					.setClientIp(ip)
					.setActName(redpack.getActName())
					.setRemark(redpack.getRemark())
					.setPaternerKey(config.getApiKey()).build();

			String sendRedPack = RedHbApi.sendRedPack(params, config.getCertPath(), config.getMchId());
			logger.info("sendRedPack>"+sendRedPack);
			Map<String, String> xmlToMap = PaymentKit.xmlToMap(sendRedPack);
			String return_code = xmlToMap.get("return_code");
			String result_code = xmlToMap.get("result_code");
			if (codeIsOK(return_code) && codeIsOK(result_code)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 判断接口返回的code是否是SUCCESS
	 * @param return_code、result_code
	 * @return
	 */
	public static boolean codeIsOK(String return_code) {
		return StrKit.notBlank(return_code) && "SUCCESS".equals(return_code);
	}
}
