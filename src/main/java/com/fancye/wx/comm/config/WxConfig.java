/**
 * 
 */
package com.fancye.wx.comm.config;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcUtils;
import com.alibaba.druid.wall.WallFilter;
import com.fancye.wx.comm.model._MappingKit;
import com.fancye.wx.comm.routes.AdminRoutes;
import com.fancye.wx.comm.routes.WeiXinRoutes;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.template.Engine;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * @author Fancye
 * 
 */
public class WxConfig extends JFinalConfig {
	
	static Log log = Log.getLog(WxConfig.class);
	private static Prop p = loadProp();
	
	public static Prop loadProp() {
		try {
			return PropKit.use("wx_config_pro.txt");
		} catch (Exception e) {
			return PropKit.use("wx_config.txt");
		}
	}
	
	public void configConstant(Constants me) {
		me.setDevMode(p.getBoolean("devMode", false));
//		me.setViewType(ViewType.JSP);
		me.setEncoding("utf-8");
		me.setError404View("/WEB-INF/error/404.html");
		me.setError500View("/WEB-INF/error/500.html");

		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}

	public void configRoute(Routes me) {
		me.add(new AdminRoutes());
		me.add(new WeiXinRoutes());
	}

	public void configEngine(Engine me) {}

	public void configPlugin(Plugins me) {
		DruidPlugin dp = createDruidPlugin();
		me.add(dp);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setDialect(new MysqlDialect());
		arp.setTransactionLevel(Connection.TRANSACTION_REPEATABLE_READ);
		arp.setShowSql(p.getBoolean("devMode", false));
		// 忽略大小设置
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		me.add(arp);
		_MappingKit.mapping(arp);
//		me.add(new NettyPlugin());
//		me.add(new RedisPlugin(null, null));
	}

	public void configInterceptor(Interceptors me) {
	}

	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctxPath"));
		
		// Druid监控
		DruidStatViewHandler dsvh = new DruidStatViewHandler("/druid", new IDruidStatViewAuth() {
			public boolean isPermitted(HttpServletRequest request) {
				return true;
			}
		});
		me.add(dsvh);
	}

	public void afterJFinalStart() {
		log.info("afterJFinalStart...");
		super.afterJFinalStart();
        // 1.5 之后支持redis存储access_token、js_ticket，需要先启动RedisPlugin
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
        // 1.6新增的2种初始化
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache(Redis.use("weixin")));
//        ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache("weixin"));

//        ApiConfig ac = new ApiConfig();
        // 配置微信 API 相关参数
//        ac.setToken(p.get("token"));
//        ac.setAppId(p.get("appId"));
//        ac.setAppSecret(p.get("appSecret"));

        /**
         *  是否对消息进行加密，对应于微信平台的消息加解密方式：
         *  1：true进行加密且必须配置 encodingAesKey
         *  2：false采用明文模式，同时也支持混合模式
         */
//        ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
//        ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));

        /**
         * 多个公众号时，重复调用ApiConfigKit.putApiConfig(ac)依次添加即可，第一个添加的是默认。
         */
//        ApiConfigKit.putApiConfig(ac);
        
        /**
         * 1.9 新增LocalTestTokenCache用于本地和线上同时使用一套appId时避免本地将线上AccessToken冲掉
         * 
         * 设计初衷：https://www.oschina.net/question/2702126_2237352
         * 
         * 注意：
         * 1. 上线时应保证此处isLocalDev为false，或者注释掉该不分代码！
         * 
         * 2. 为了安全起见，此处可以自己添加密钥之类的参数，例如：
         * http://localhost/weixin/api/getToken?secret=xxxx
         * 然后在WeixinApiController#getToken()方法中判断secret
         * 
         * @see WeixinApiController#getToken()
         */
//        if (isLocalDev) {
//            String onLineTokenUrl = "http://localhost/weixin/api/getToken";
//            ApiConfigKit.setAccessTokenCache(new LocalTestTokenCache(onLineTokenUrl));
//        }
        /*WxaConfig wc = new WxaConfig();
        wc.setAppId("wx4f53594f9a6b3dcb");
        wc.setAppSecret("eec6482ba3804df05bd10895bace0579");
        WxaConfigKit.setWxaConfig(wc);*/
    }
	
	public static DruidPlugin getDruidPlugin() {
		String jdbcUrl = PropKit.get("jdbcUrl");
		String user = PropKit.get("user");
		String password = PropKit.get("password").trim();
		System.out.println("load data source: "+jdbcUrl + " " + user + " " + password);
		
		return new DruidPlugin(jdbcUrl, user, password);
	}
	
	public static DruidPlugin createDruidPlugin() {
		// 配置druid数据连接池插件
		DruidPlugin dp = getDruidPlugin();
		
		// 配置druid监控
		dp.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType(JdbcUtils.MYSQL);
		dp.addFilter(wall);
		
		return dp;
	}
}
