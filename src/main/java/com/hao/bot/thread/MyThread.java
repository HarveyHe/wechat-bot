package com.hao.bot.thread;

import me.biezhi.wechat.Constant;
import me.biezhi.wechat.WechatRobot;


/**
 * 监听登陆状态
 * @author Harvey.He
 *
 */
public class MyThread extends Thread {
    private WechatRobot wechatRobot;
    public MyThread(WechatRobot wechatRobot) {
        this.wechatRobot = wechatRobot;
    }

    public void run() {
    	while(!Constant.HTTP_OK.equals(wechatRobot.waitForLogin())){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		wechatRobot.closeQrWindow();
		wechatRobot.start();
    }

   
}
