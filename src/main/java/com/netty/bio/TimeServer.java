package com.netty.bio;

import com.netty.oio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jing on 2016/10/10.
 */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8080 ;

        ServerSocket serverSocket = null ;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("the time server is start in port:"+port);
            Socket accept = null ;
            while (true){
                accept = serverSocket.accept();
                new TimeServerHandler(accept).run();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
