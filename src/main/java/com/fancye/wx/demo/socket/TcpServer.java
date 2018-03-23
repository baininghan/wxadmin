/**
 * 
 */
package com.fancye.wx.demo.socket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author Fancye
 * 
 */
public class TcpServer {

	// 循环事件处理组
	static final EventLoopGroup bossGroup = new NioEventLoopGroup();
	static final EventLoopGroup workerGroup = new NioEventLoopGroup();

	/**
	 * 运行
	 * 
	 * @param host
	 * @param port
	 */
	public static void run(String host, int port) {
		ServerBootstrap server = new ServerBootstrap();
		server.group(bossGroup, workerGroup);
		server.channel(NioServerSocketChannel.class);
		server.childHandler(new ChannelInitializer() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder",
						new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,
								4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder",
						new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("encoder",
						new StringEncoder(CharsetUtil.UTF_8));
				pipeline.addLast(new TcpServerHandler());
			}
		});
		server.option(ChannelOption.SO_BACKLOG, 128);
		server.childOption(ChannelOption.SO_KEEPALIVE, true);
		try {
			ChannelFuture cf = server.bind(host, port).sync();
			cf.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			shutdown();
		}
	}

	/**
	 * 服务器端消息处理
	 */
	static class TcpServerHandler extends SimpleChannelInboundHandler {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Object msg)
				throws Exception {
			System.out.println("客户端消息>>>> " + msg);
			ctx.channel().writeAndFlush("您好，客户端，我是服务器端！你发送的消息是：" + msg);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
				throws Exception {
			cause.printStackTrace();
			ctx.close();
		}

	}

	/**
	 * 关闭
	 */
	protected static void shutdown() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 服务地址
		String ip = "0.0.0.0";
		// 端口
		int port = 51234;
		TcpServer.run(ip, port);
		
	}

}
