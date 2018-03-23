/**
 * 
 */
package com.fancye.wx.comm.plugin;

import com.fancye.wx.demo.socket.TcpServer;
import com.jfinal.plugin.IPlugin;

/**
 * @author Fancye
 *
 */
public class NettyPlugin implements IPlugin {

	public boolean start() {
		TcpServer.run("192.168.1.60", 51234);
		return true;
	}

	public boolean stop() {
		return false;
	}

}
