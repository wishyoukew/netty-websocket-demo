/**  
 * Copyright © 2018成都淞幸科技有限责任公司. All rights reserved.
 *
 * @Title: UserInfoManager.java
 * @Prject: nngr
 * @Package: demo
 * @Description: TODO
 * @author: HeWanLi  
 * @date: 2018年3月29日 下午2:11:55
 * @version: V1.0.0
 */
package com.sunsheen.websocket.netty;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.hibernate.HibernateException;

import com.sunsheen.jfids.system.database.DBSession;
import com.sunsheen.jfids.util.DataBaseUtil;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @filename:UserInfoManager.java
 * @filedescription:
 * @copyright:版权所有(C)2009-2050
 * @company:成都淞幸科技有限责任公司
 * @summary:
 * @othersummary:
 * @finisheddate:
 * @modifyrecords:
 * @version:V1.0.0
 * @author:HeWanLi
 * @date:2018年3月29日下午2:11:55
 */
public class UserInfoManager {
	private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    private static ConcurrentHashMap<Channel, UserInfo> userInfos = new ConcurrentHashMap<>();
    
    /**
     * 登录注册 channel
     *
     *  
     */
    public static void addChannel(Channel channel,String uid) {
        String remoteAddr = NettyUtil.parseChannelRemoteAddr(channel);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(uid);
        userInfo.setAddr(remoteAddr);
        userInfo.setChannel(channel);
        userInfos.put(channel, userInfo);
    }

    /**
     * 普通消息
     *
     * @param message
     */
    public static void broadcastMess(String uid,String message,String sender) {
        if (!BlankUtil.isBlank(message)) {
            try {
                rwLock.readLock().lock();
                Set<Channel> keySet = userInfos.keySet();
                for (Channel ch : keySet) {
                    UserInfo userInfo = userInfos.get(ch);
                    if (!userInfo.getUserId().equals(uid) ) continue;
                    String backmessage=sender+","+message;
                    ch.writeAndFlush(new TextWebSocketFrame(backmessage));
                  /*  responseToClient(ch,message);*/
                }
            } finally {
                rwLock.readLock().unlock();
            }
        }
     }

	/**
	 * @param channel
	 */
	public static void removeChannel(Channel channel) {
		// TODO 自动生成的方法存根
		userInfos.remove(channel);
	}

	/**
	 * @param channel
	 * @return
	 */
	public static UserInfo getUserInfo(Channel channel) {
		// TODO 自动生成的方法存根
		return userInfos.get(channel);
	}

	/**
	 * 添加房间号信息,注册信息
	 * @param channel
	 * @param changeStringToMap
	 */
	public static void addChannel(Channel channel,
			Map<String, Object> paramMap) {
		removeChannel(channel);
		/*if(!rooms.containsKey(paramMap.get("room_id"))){
			// 并将聊天室添加到数据库 判断没有的情况下,才添加到数据库
			//new WebsocketService().save(paramMap);
		}*/
		// 并将聊天室添加到数据库 判断没有的情况下,才添加到数据库
		//new WebsocketService().save(paramMap);
		String remoteAddr = NettyUtil.parseChannelRemoteAddr(channel);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(paramMap.get("uid").toString());
        userInfo.setAddr(remoteAddr);
        userInfo.setSender(paramMap.get("sender").toString());
        userInfo.setChannel(channel);
        userInfos.put(channel, userInfo);
        // rooms.put(paramMap.get("room_id").toString(), userInfos);	// 添加一个聊天室
        System.out.print("connect user:"+paramMap.get("uid").toString());
        // 将消息添加到数据库
       //new WebsocketService().saveMessage(paramMap);
	}
}
