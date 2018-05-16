package com.sunsheen.websocket.start;

import com.sunsheen.websocket.netty.ImServer;
import com.sunsheen.websocket.netty.NettyUtil;


/**
 * 利用启动的时候启动websocket
 * @author HeWanLi
 *
 */
public class FtpTimingTask {
	
	public void timingTask(){
		
	}
	
	/**
	 * 定时执行任务
	 */
	public void startTask(){
		/**
		 * 开启一个线程,让websocket服务也启动起来
		 */
		
		/*检测端口是否被占用*/
		int port = new ImServer().getPort();
		if(NettyUtil.isPortAvailable(port)){
			System.out.println("启动了websocket");
			Runnable run=new Runnable() {
				@SuppressWarnings("static-access")
				public void run(){
					try {
						new ImServer(port).run();
						System.out.println("启动了websocket");
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			};
			new Thread(run).start();
		}
	}

}
