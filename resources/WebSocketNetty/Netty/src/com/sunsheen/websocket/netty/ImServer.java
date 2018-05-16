/**  
 * Copyright © 2018成都淞幸科技有限责任公司. All rights reserved.
 *
 * @Title: ImServer.java
 * @Prject: nngr
 * @Package: demo
 * @Description: TODO
 * @author: HeWanLi  
 * @date: 2018年3月29日 下午2:02:04
 * @version: V1.0.0
 */
package com.sunsheen.websocket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * @filename:ImServer.java
 * @filedescription:
 * @copyright:版权所有(C)2009-2050
 * @company:成都淞幸科技有限责任公司
 * @summary:
 * @othersummary:
 * @finisheddate:
 * @modifyrecords:
 * @version:V1.0.0
 * @author:HeWanLi
 * @date:2018年3月29日下午2:02:04
 */
public class ImServer {
	private io.netty.channel.Channel channel;
    private  EventLoopGroup bossGroup = new NioEventLoopGroup();
    private  EventLoopGroup workerGroup = new NioEventLoopGroup();
    private static int port=8080;
    
    /**
	 * @return port
	 */
	public static int getPort() {
		return port;
	}
	
	public ImServer() {

    }
	
	//线程池设计的定时任务类
    public ImServer(int port) {

    }
    public void run() throws Exception {
        try {
            //创建ServerBootstrap实例
            ServerBootstrap b = new ServerBootstrap();  
           //设置并绑定Reactor线程池
            b.group(bossGroup, workerGroup)
            //设置并绑定服务端Channel
             .channel(NioServerSocketChannel.class)  
             .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("http-codec", new HttpServerCodec()); 
                        pipeline.addLast("aggregator", new HttpObjectAggregator(65536)); // Http消息组装  
                        pipeline.addLast("http-chunked", new ChunkedWriteHandler()); // WebSocket通信支持  
                        pipeline.addLast(new SocketHandler());//自定义处理类

                    }
             })
             .option(ChannelOption.SO_BACKLOG, 128)  
             .childOption(ChannelOption.SO_KEEPALIVE, true);
//          System.out.println("WebsocketChatServer Start:" + port);
            try {
                ChannelFuture f = b.bind(port).sync();//// 服务器异步创建绑定

                channel = f.channel();
                f.channel().closeFuture().sync();
            } catch (InterruptedException e) {

            }
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            channel.closeFuture().syncUninterruptibly();
            System.out.println("WebsocketChatServer Stop:" + port);
        }
    }
    public static void main(String[] args) throws Exception {
        new ImServer(port).run();
    }
}
