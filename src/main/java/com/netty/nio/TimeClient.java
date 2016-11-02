package com.netty.nio;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.nio
 * @Description:
 * @date 2016/6/24 18:00
 */
public class TimeClient {
    public static void main(String[] args) {
        new Thread(new TimeClientHandler("127.0.0.1",8080),"TimeClient-thread").start();
    }
}
