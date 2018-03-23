/**
 * Copyright (c) 2011-2017, Fancye Bai (lvsedehuanxiang@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.fancye.wx.comm.controller;

import java.util.ArrayList;
import java.util.List;

import com.fancye.wx.comm.interceptor.ConfigInterceptor;
import com.fancye.wx.comm.kit.URLCodeKit;
import com.fancye.wx.comm.model.Config;
import com.fancye.wx.comm.model.Keyword;
import com.fancye.wx.comm.model.Material;
import com.fancye.wx.comm.model.Replymessage;
import com.fancye.wx.comm.model.Submsg;
import com.fancye.wx.comm.model.User;
import com.fancye.wx.comm.service.ConfigService;
import com.fancye.wx.comm.service.KeywordService;
import com.fancye.wx.comm.service.MaterialService;
import com.fancye.wx.comm.service.WxUserService;
import com.fancye.wx.comm.utils.RedPackUtil;
import com.jfinal.aop.Before;
import com.jfinal.kit.JsonKit;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.CustomServiceApi;
import com.jfinal.weixin.sdk.api.CustomServiceApi.Articles;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.kit.IpKit;
import com.jfinal.weixin.sdk.msg.in.InTextMsg;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.out.News;
import com.jfinal.weixin.sdk.msg.out.OutImageMsg;
import com.jfinal.weixin.sdk.msg.out.OutNewsMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

/**
 * @author Fancye
 * @date 2018-3-22 下午7:36:36
 * 
 */
@Before(ConfigInterceptor.class)
public class WeixinMsgController extends MsgControllerAdapter {

	static Log log = Log.getLog(WeixinMsgController.class);
	private static final String helpStr = "\t你的品位不错哦  么么哒 -By Fancye。";

	/**
	 * 关注/取消关注事件
	 */
	@Override
	protected void processInFollowEvent(InFollowEvent inFollowEvent) {
		if (InFollowEvent.EVENT_INFOLLOW_SUBSCRIBE.equals(inFollowEvent
				.getEvent())) {
			Config config = (Config) getSession().getAttribute("config");
			final String appId = config.getAppId();
			final String openId = inFollowEvent.getFromUserName();
			// 公众号的类型 1=测试号,2=订阅号,3=认证订阅号,4=服务号,5=认证服务号,6=企业号,7=认证企业号
			Integer appType = config.getAppType();
			log.info("关注：" + inFollowEvent.getEvent());

			if (appType == 1 || appType == 3 || appType == 5) {
				ApiResult userInfo = UserApi.getUserInfo(openId);
				if (userInfo.isSucceed()) {
					WxUserService service = new WxUserService();
					final User user = service.save(appId, userInfo);
					if (user != null) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								// 客服接口发送异步消息
								StringBuffer sbf = new StringBuffer();
								sbf.append("欢迎")
										.append("【")
										.append(URLCodeKit.decode(user
												.getNickName())).append("】")
										.append("关注。");
								CustomServiceApi.sendText(openId,
										sbf.toString());
							}
						}).start();
					}
				}
			}
		}

		OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
		outMsg.setContent(helpStr);
		render(outMsg);

		if (InFollowEvent.EVENT_INFOLLOW_UNSUBSCRIBE.equals(inFollowEvent
				.getEvent())) {
			log.debug("取消关注：" + inFollowEvent.getFromUserName());
			renderNull();
		}
	}

	/**
	 * 文本消息事件
	 */
	@Override
	protected void processInTextMsg(InTextMsg inTextMsg) {
		String msgContent = inTextMsg.getContent().trim();
		final Config config = ConfigService.s.getByRmid(getPara("rmid"));
		final String openId = inTextMsg.getFromUserName();
		int appType = config.getAppType();

		List<Keyword> keywords = KeywordService.s.list(config.getAppId(), msgContent);

		if (null != keywords && keywords.size() > 0) {
			int size = keywords.size();
			Keyword keyword = keywords.get(0);

			// 异步发送其他的消息
			if (size >= 1 && (appType == 1 || appType == 3 || appType == 5)) {
				for (int i = 1; i < size; i++) {
					Keyword asyncKeyword = keywords.get(i);
					Integer async = asyncKeyword.getAsync();
					if (async == 1) {
						replyMessage(1, true, config, openId, asyncKeyword, appType);
					}
				}
			}

			// 根据关键字被动回复消息
			replyMessage(1, false, config, openId, keyword, appType);
		}

		if (msgContent.equals("红包")) {
			new Thread(new Runnable() {
				public void run() {
					log.info("是发红包的时候了...");
					RedPackUtil.sendRedPack(config, 1, openId,
							IpKit.getRealIp(getRequest()));
				}
			}).start();
		}

		renderOutTextMsg(inTextMsg.getContent());
	}

	/**
	 * 
	 * @param actionType
	 *            1 关键字回复 2 关注回复 3 回复消息
	 * @param isCustomMessage
	 *            是否是客服消息
	 * @param config
	 * @param openId
	 * @param msgObj
	 *            消息对象体
	 * @param appType
	 *            消息类型:1=文本,2=图文,3=图片,4=红包
	 */
	public void replyMessage(int actionType, boolean isCustomMessage,
			final Config config, final String openId, Object msgObj, int appType) {
		String content = null;
		String mediaId = null;
		String articleKey = null;
		int redpackId = 0;

		int messageType = 0;

		if (actionType == 1) {
			Keyword keyword = (Keyword) msgObj;
			content = keyword.getContent();
			mediaId = keyword.getMediaId();
			messageType = keyword.getType();
			articleKey = content;
		} else if (actionType == 2) {
			Submsg submsg = (Submsg) msgObj;
			content = submsg.getContent();
			mediaId = submsg.getMediaId();
			messageType = submsg.getType();
			articleKey = submsg.getArticleKey();
			redpackId = submsg.getRedpackId();
		} else if (actionType == 3) {
			Replymessage replymessage = (Replymessage) msgObj;
			content = replymessage.getContent();
			mediaId = replymessage.getMediaId();
			messageType = replymessage.getType();
			articleKey = replymessage.getArticleKeyword();
			redpackId = replymessage.getRedpackId();
		}

		if (isCustomMessage) {
			// 客服接口发现消息
			if (messageType == 1) {
				ApiResult sendText = CustomServiceApi.sendText(openId, content);
				log.info("异步发送文本:" + sendText.getJson());
			} else if (messageType == 2) {
				// 图文 通过文章的关键字查询回复的图文
				String key = articleKey.trim();
				log.info("articleKey>" + key);
				@SuppressWarnings("unchecked")
				List<Articles> news = (List<Articles>) getNews(config.getAppId(), key, 2);
				ApiResult sendNews = CustomServiceApi.sendNews(openId, news);
				log.info("异步发送图文:" + sendNews.getJson());
			} else if (messageType == 3) {
				ApiResult sendImage = CustomServiceApi.sendImage(openId, mediaId);
				log.info("异步发送图片:" + sendImage.getJson());
			}
		} else {
			// 被动回复消息
			if (messageType == 1) {
				renderOutTextMsg(content);
				return;
			} else if (messageType == 2) {
				// 通过关键字查询图文
				OutNewsMsg outMsg = new OutNewsMsg(getInMsg());
				@SuppressWarnings("unchecked")
				List<News> news = (List<News>) getNews(config.getAppId(), articleKey, 1);
				outMsg.setArticles(news);
				return;
			} else if (messageType == 3) {
				renderOutImageMsg(mediaId);
				return;
			} else if (messageType == 4) {
				// 红包
				// 认证的服务号才有发红包的权限
				if (appType == 5) {
					final int readId = redpackId;
					// 异步判断是否需要发红包
					new Thread(new Runnable() {
						@Override
						public void run() {
							// 判断是否需要发红包
							log.info("readpackId>" + readId);
							RedPackUtil.sendRedPack(config, readId, openId,
									IpKit.getRealIp(getRequest()));
						}
					}).start();
				}
				return;
			}
		}
	}

	public void renderOutImageMsg(String mediaId) {
		OutImageMsg outMsg = new OutImageMsg(getInMsg());
		outMsg.setMediaId(mediaId);
		render(outMsg);
	}
	
	public static Object getNews(String appId, String article_key, int articleType) {
		List<Material> list = MaterialService.s.getMaterialBySKI(appId, article_key);
		log.info("material list>" + JsonKit.toJson(list));
		if (null != list && list.size() > 0) {
			if (articleType == 1) {
				List<News> articles = new ArrayList<News>();
				for (Material material : list) {
					News news = new News();
					news.setTitle(material.getTitle());
					String url = material.getUrl();
					if (StrKit.notBlank(url)) {
						news.setUrl(url);
					} else {
						news.setUrl(PropKit.get("domain") + "/material/getById?id=" + material.getId());
					}
					news.setPicUrl(material.getImageUrl());
					news.setDescription(material.getDescribe());
					articles.add(news);
				}
				return articles;
			}else if (articleType == 2) {
				List<Articles> articles = new ArrayList<Articles>();
				for (Material material : list) {
					Articles article = new Articles();
					article.setTitle(material.getTitle());
					article.setDescription(material.getDescribe());
					article.setPicurl(material.getImageUrl());
					String url = material.getUrl();
					if (StrKit.notBlank(url)) {
						article.setUrl(url);
					} else {
						article.setUrl(PropKit.get("domain") + "/material/getById?id=" + material.getId());
					}
					
					articles.add(article);
				}
				return articles;
			}
		}
		return null;
	}

	/**
	 * 菜单事件
	 */
	@Override
	protected void processInMenuEvent(InMenuEvent inMenuEvent) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("事件类型：").append(inMenuEvent.getEvent()).append("\n")
				.append("消息类型：").append(inMenuEvent.getMsgType())
				.append("\n")
				// sbf.append("扫码内容：").append(inMenuEvent.getScanCodeInfo().getScanResult()).append("\n")
				.append("openId：").append(inMenuEvent.getFromUserName())
				.append("\n").append("事件Key：")
				.append(inMenuEvent.getEventKey()).append("\n");
		renderOutTextMsg(sbf.toString());
	}

}
