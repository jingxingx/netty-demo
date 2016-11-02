package com.netty.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.oio
 * @Description:
 * @date 2016/6/23 15:31
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("QUERY TIME ORDER");
            String resp = in.readLine();
            System.out.println("Now is:"+resp);
            Thread.sleep(1000);
            out.println("QUERY TIME ORDER");
//            System.out.println("Send order server success!");
            resp = in.readLine();
            System.out.println("Now is:"+resp);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (out != null) {
                out.close();
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
