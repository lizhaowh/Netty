package com.mashibing.nettynio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketIO {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9090);

        System.out.println("step1: new ServerSocket(9090) ");

        Socket client = server.accept();  //阻塞1

        System.out.println("step2:client\t"+client.getPort());

        InputStream in = client.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println(reader.readLine());  //阻塞2

        while(true){
        }
    }


}
