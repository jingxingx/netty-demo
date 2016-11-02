package com.netty.nio;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.nio
 * @Description:
 * @date 2016/6/23 17:57
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080 ;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"MultiplexerTimeServer-02").start();
    }
}
