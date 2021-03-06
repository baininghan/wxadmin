package com.fancye.wx.comm.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseSubmsg<M extends BaseSubmsg<M>> extends Model<M> implements IBean {

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

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setRedpackId(java.lang.Integer redpackId) {
		set("redpack_id", redpackId);
	}
	
	public java.lang.Integer getRedpackId() {
		return getInt("redpack_id");
	}

	public void setArticleKey(java.lang.String articleKey) {
		set("article_key", articleKey);
	}
	
	public java.lang.String getArticleKey() {
		return getStr("article_key");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setMediaId(java.lang.String mediaId) {
		set("media_id", mediaId);
	}
	
	public java.lang.String getMediaId() {
		return getStr("media_id");
	}

	public void setSort(java.lang.Integer sort) {
		set("sort", sort);
	}
	
	public java.lang.Integer getSort() {
		return getInt("sort");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
