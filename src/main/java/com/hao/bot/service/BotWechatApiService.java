package com.hao.bot.service;    

import com.blade.kit.json.JSONObject;
import com.gsst.eaf.core.service.BaseService;

import me.biezhi.wechat.model.WechatMeta;

/**
 * 
 * @author Harvey.He
 *
 */
public interface BotWechatApiService extends BaseService {

    
    /**
     * 注入
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName, Object singletonObject);
    
    
    /**
     * 发送文本消息
     *
     * @param toUser
     * @param msg
     */
    boolean sendText(WechatMeta meta,String toUser, String msg);

    /**
     * 发送图片
     *
     * @param toUser
     * @param filePath
     */
    boolean sendImg(WechatMeta meta,String toUser, String filePath);
    
    /**
     * 获取群信息
     * @param groudName
     * @return
     */
    JSONObject getGroudAccount(String groudName);
    
}