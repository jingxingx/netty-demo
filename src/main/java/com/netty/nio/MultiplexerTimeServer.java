package com.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.nio
 * @Description:
 * @date 2016/6/23 17:58
 */
public class MultiplexerTimeServer implements Runnable{

    private Selector selector ;

    private ServerSocketChannel socketChannel ;

    private volatile boolean stop ;

    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.bind(new InetSocketAddress(port),1024);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:"+port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        stop = true ;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> it = set.iterator();
                SelectionKey key = null ;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = socketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector,SelectionKey.OP_READ);
        }
        if (key.isReadable()){
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readBytes = channel.read(buffer);
            if (readBytes > 0) {
                buffer.flip();
                byte [] bytes = new byte[buffer.remaining()];
                String body = new String(bytes,"UTF-8");
                System.out.println("The time server receive order :"+body);
                String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                doWrite(channel,currentTime);
            } else if (readBytes < 0){
                key.cancel();
                channel.close();
            } else ;

        }
    }

    private void doWrite(SocketChannel channel, String currentTime) throws IOException {
        if (currentTime != null && currentTime.length()>0) {
            byte [] bytes = currentTime.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);

        }
    }
}
