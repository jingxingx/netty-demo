package com.netty.iopoll;

import com.netty.oio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.iopoll
 * @Description:
 * @date 2016/6/23 16:04
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080 ;
        ServerSocket server = null;


        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:"+port);
            TimeServerHandlerExecutePool executePool = new TimeServerHandlerExecutePool(30,10000);
            while (true){
                Socket socket = server.accept() ;
                executePool.executor(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
