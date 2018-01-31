package com.hao.bot.entity;

public class CommonRequest<T> {
    private T request;
    public void setRequest(T request) {
        this.request = request;
    }
    public T getRequest() {
        return request;
    }
}
