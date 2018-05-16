/**  
 * Copyright © 2018成都淞幸科技有限责任公司. All rights reserved.
 *
 * @Title: NettyUtil.java
 * @Prject: nngr
 * @Package: demo
 * @Description: TODO
 * @author: HeWanLi  
 * @date: 2018年3月29日 下午2:34:02
 * @version: V1.0.0
 */
package com.sunsheen.websocket.netty;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

import com.google.gson.Gson;

import io.netty.channel.Channel;

/**
 * @filename:NettyUtil.java
 * @filedescription:
 * @copyright:版权所有(C)2009-2050
 * @company:成都淞幸科技有限责任公司
 * @summary:
 * @othersummary:
 * @finisheddate:
 * @modifyrecords:
 * @version:V1.0.0
 * @author:HeWanLi
 * @date:2018年3月29日下午2:34:02
 */
public class NettyUtil {

	/**
	 * @param channel
	 * @return
	 */
	public static String parseChannelRemoteAddr(Channel channel) {
		// TODO 自动生成的方法存根
		return channel.remoteAddress().toString().substring(1);
	}

	/**
	 * 将字符串转成Map
	 * @param text  {"room_id":"1","uid":"1","sender":"2","type":"1","message":"1"}  这样的格式
	 * @return
	 */
	public static Map<String, Object> changeStringToMap(String text){
		Gson gson = new Gson();
		Map<String, Object> paramMap = gson.fromJson(text, Map.class);
		return paramMap;
	}
	
	private static void bindPort(String host, int port) throws Exception {
	    Socket s = new Socket();
	    s.bind(new InetSocketAddress(host, port));
	    s.close();
	}
	public static boolean isPortAvailable(int port) {
	    Socket s = new Socket();
	    try {
	        bindPort("0.0.0.0", port);
	        bindPort(InetAddress.getLocalHost().getHostAddress(), port);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
}
