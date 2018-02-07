package com.hao.bot.entity;

/**
 * 
 * @author Harvey.He
 *
 * @param <T>
 */
public class CommonRequest<T> {
    private T request;
    public void setRequest(T request) {
        this.request = request;
    }
    public T getRequest() {
        return request;
    }
}
