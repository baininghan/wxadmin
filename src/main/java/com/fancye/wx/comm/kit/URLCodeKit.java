/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.kit;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/** 
 * @author Fancye
 * @date 2018-3-23 下午12:02:29 
 *  
 */
public class URLCodeKit {

	public static String decode(String decodeStr) {
		try {
			return URLDecoder.decode(decodeStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decodeStr;
	}
	
	public static String encode(String encodeStr){
		try {
			return URLEncoder.encode(encodeStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
}
