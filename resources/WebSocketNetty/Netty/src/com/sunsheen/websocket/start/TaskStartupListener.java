package com.sunsheen.websocket.start;

import javax.servlet.ServletContext;

import com.sunsheen.jfids.system.servlet.Listener;
import com.sunsheen.jfids.system.servlet.SystemStartupListener;

/**
 * 服务启动的时候就执行监听
 * @author heWanLi
 * 2018-01-03
 *
 */


@Listener
public class TaskStartupListener implements SystemStartupListener {

	@Override
	public void init(ServletContext param) {
		new FtpTimingTask().startTask();
	}

}
