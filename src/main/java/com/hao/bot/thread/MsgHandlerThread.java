package com.hao.bot.thread;

import com.hao.bot.demo.MyWechatBot;

import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.DateUtils;


/**
 * 监听登陆状态
 * @author Harvey.He
 *
 */
public class MsgHandlerThread extends Thread {
    private MyWechatBot myWechatBot;
    public MsgHandlerThread(MyWechatBot myWechatBot) {
        this.myWechatBot = myWechatBot;
    }
    @Override
    public void run() {
        while (true) {
            if (myWechatBot.hasMessage()) {
                WeChatMessage weChatMessage = myWechatBot.nextMessage();
                myWechatBot.callBack(myWechatBot.getMapping().get(MsgType.ALL), weChatMessage);
                myWechatBot.callBack(myWechatBot.getMapping().get(weChatMessage.getMsgType()), weChatMessage);
            } else {
                DateUtils.sleep(50);
            }
        }
    }

   
}
