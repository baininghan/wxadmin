package com.fancye.wx.comm.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("wx_admin", "id", Admin.class);
		arp.addMapping("wx_authorize", "id", Authorize.class);
		arp.addMapping("wx_config", "id", Config.class);
		arp.addMapping("wx_custommenu", "id", Custommenu.class);
		arp.addMapping("wx_keyword", "id", Keyword.class);
		arp.addMapping("wx_matchrulemenu", "id", Matchrulemenu.class);
		arp.addMapping("wx_material", "id", Material.class);
		arp.addMapping("wx_menutype", "id", Menutype.class);
		arp.addMapping("wx_message", "id", Message.class);
		arp.addMapping("wx_qrcode", "id", Qrcode.class);
		arp.addMapping("wx_redpack", "id", Redpack.class);
		arp.addMapping("wx_replymessage", "id", Replymessage.class);
		arp.addMapping("wx_submsg", "id", Submsg.class);
		arp.addMapping("wx_user", "id", User.class);
	}
}

