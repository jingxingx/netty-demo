package com.netty.oio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.oio
 * @Description:
 * @date 2016/6/23 11:19
 */
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port :"+port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("Time server close!");
                serverSocket.close();
            }
        }
    }
}
