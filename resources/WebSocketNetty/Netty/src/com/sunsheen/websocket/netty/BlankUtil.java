/**  
 * Copyright © 2018成都淞幸科技有限责任公司. All rights reserved.
 *
 * @Title: BlankUtil.java
 * @Prject: nngr
 * @Package: demo
 * @Description: TODO
 * @author: HeWanLi  
 * @date: 2018年3月29日 下午2:26:33
 * @version: V1.0.0
 */
package com.sunsheen.websocket.netty;

/**
 * @filename:BlankUtil.java
 * @filedescription:
 * @copyright:版权所有(C)2009-2050
 * @company:成都淞幸科技有限责任公司
 * @summary:
 * @othersummary:
 * @finisheddate:
 * @modifyrecords:
 * @version:V1.0.0
 * @author:HeWanLi
 * @date:2018年3月29日下午2:26:33
 */
public class BlankUtil {
	public static boolean isBlank(String messges){
		if (null == messges || "".equals(messges)) {
			return true;
		}
		return false;
	}
}
