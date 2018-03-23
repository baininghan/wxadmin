package com.fancye.wx.comm.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMaterial<M extends BaseMaterial<M>> extends Model<M> implements IBean {

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

	public void setKeyword(java.lang.String keyword) {
		set("keyword", keyword);
	}
	
	public java.lang.String getKeyword() {
		return getStr("keyword");
	}

	public void setType(java.lang.Integer type) {
		set("type", type);
	}
	
	public java.lang.Integer getType() {
		return getInt("type");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setImageUrl(java.lang.String imageUrl) {
		set("image_url", imageUrl);
	}
	
	public java.lang.String getImageUrl() {
		return getStr("image_url");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return getStr("title");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setDescribe(java.lang.String describe) {
		set("describe", describe);
	}
	
	public java.lang.String getDescribe() {
		return getStr("describe");
	}

	public void setAuthor(java.lang.String author) {
		set("author", author);
	}
	
	public java.lang.String getAuthor() {
		return getStr("author");
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
