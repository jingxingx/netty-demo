package com.netty.nio;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.nio
 * @Description:
 * @date 2016/6/24 18:02
 */
public class TimeClientHandler implements Runnable{

    private String host ;
    private int port ;
    private Selector selector ;
    private SocketChannel socketChannel ;

    private volatile boolean stop ;

    public TimeClientHandler(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {

    }
}
