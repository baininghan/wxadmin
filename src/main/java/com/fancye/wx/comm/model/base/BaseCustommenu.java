package com.fancye.wx.comm.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCustommenu<M extends BaseCustommenu<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public void setPid(java.lang.Integer pid) {
		set("pid", pid);
	}
	
	public java.lang.Integer getPid() {
		return getInt("pid");
	}

	public void setAppId(java.lang.String appId) {
		set("app_id", appId);
	}
	
	public java.lang.String getAppId() {
		return getStr("app_id");
	}

	public void setTypeId(java.lang.Integer typeId) {
		set("type_id", typeId);
	}
	
	public java.lang.Integer getTypeId() {
		return getInt("type_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setUrl(java.lang.String url) {
		set("url", url);
	}
	
	public java.lang.String getUrl() {
		return getStr("url");
	}

	public void setKey(java.lang.String key) {
		set("key", key);
	}
	
	public java.lang.String getKey() {
		return getStr("key");
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

	public void setXappid(java.lang.String xappid) {
		set("xappid", xappid);
	}
	
	public java.lang.String getXappid() {
		return getStr("xappid");
	}

	public void setPagepath(java.lang.String pagepath) {
		set("pagepath", pagepath);
	}
	
	public java.lang.String getPagepath() {
		return getStr("pagepath");
	}

	public void setMenuGroup(java.lang.Integer menuGroup) {
		set("menu_group", menuGroup);
	}
	
	public java.lang.Integer getMenuGroup() {
		return getInt("menu_group");
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

	public void setRemake(java.lang.String remake) {
		set("remake", remake);
	}
	
	public java.lang.String getRemake() {
		return getStr("remake");
	}

}