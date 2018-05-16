/**  
 * Copyright © 2018成都淞幸科技有限责任公司. All rights reserved.
 *
 * @Title: UserInfo.java
 * @Prject: nngr
 * @Package: demo
 * @Description: TODO
 * @author: HeWanLi  
 * @date: 2018年3月29日 下午2:08:03
 * @version: V1.0.0
 */
package com.sunsheen.websocket.netty;

import io.netty.channel.Channel;

/**
 * @filename:UserInfo.java
 * @filedescription:
 * @copyright:版权所有(C)2009-2050
 * @company:成都淞幸科技有限责任公司
 * @summary:
 * @othersummary:
 * @finisheddate:
 * @modifyrecords:
 * @version:V1.0.0
 * @author:HeWanLi
 * @date:2018年3月29日下午2:08:03
 */
public class UserInfo {
	private String userId;  // UID
    private String addr;    // 地址
    private Channel channel;// 通道
    private String sender;  // 消息接收人

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	/**
	 * @return sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender 要设置的 sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
    
}
