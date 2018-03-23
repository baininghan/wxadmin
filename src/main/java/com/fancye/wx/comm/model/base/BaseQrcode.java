package com.fancye.wx.comm.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseQrcode<M extends BaseQrcode<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setAppId(java.lang.String appId) {
		set("app_id", appId);
	}
	
	public java.lang.String getAppId() {
		return getStr("app_id");
	}

	public void setSceneStr(java.lang.String sceneStr) {
		set("scene_str", sceneStr);
	}
	
	public java.lang.String getSceneStr() {
		return getStr("scene_str");
	}

	public void setSceneId(java.lang.Integer sceneId) {
		set("scene_id", sceneId);
	}
	
	public java.lang.Integer getSceneId() {
		return getInt("scene_id");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setExpireSeconds(java.lang.Integer expireSeconds) {
		set("expire_seconds", expireSeconds);
	}
	
	public java.lang.Integer getExpireSeconds() {
		return getInt("expire_seconds");
	}

	public void setTicket(java.lang.String ticket) {
		set("ticket", ticket);
	}
	
	public java.lang.String getTicket() {
		return getStr("ticket");
	}

	public void setMessageId(java.lang.String messageId) {
		set("message_id", messageId);
	}
	
	public java.lang.String getMessageId() {
		return getStr("message_id");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

}
