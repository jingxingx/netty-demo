package com.netty.oio;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.oio
 * @Description:
 * @date 2016/6/23 11:27
 */
public class TimeServerHandler implements Runnable {
    private Socket socket ;
    private volatile static int count = 1 ;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null ;
        PrintWriter out = null ;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(),true);
            String currentTime = null ;
            String body = null ;
            while (true){
                body = in.readLine();
                if (body == null){
                    break;
                }
                System.out.println("The time server receive order :"+body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                if (count == 1){
                    count ++ ;
                    Thread.sleep(1000 * 3);
                    out.println(currentTime);
                } else {
                    out.println(currentTime);
                }
            }
        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                if (out != null) {
                    out.close();
                }

                if (this.socket != null) {
                    try {
                        this.socket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
