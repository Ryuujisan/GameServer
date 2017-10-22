package io.yuri.yuriserver.server;


import io.netty.channel.local.LocalAddress;
import io.yuri.yuriserver.lobby.AbstractGameMenager;
import io.yuri.yuriserver.utils.ServerLog;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable{

    private static Server instance;

    private ServerSocket serverSocket = null;

    private final AbstractGameMenager gameMenager;
    private final short port;
    private boolean runnable = false;

    public Server(AbstractGameMenager gameMenager, short port) throws Exception {
        instance = this;
        this.gameMenager = gameMenager;
        this.port = port;


        start();
    }

    public void start() throws Exception {

        try {
            serverSocket = new ServerSocket(port);
            //serverSocket.bind(new InetSocketAddress(port));
            ServerLog.DebugLog("Create server");

        } catch (Exception e) {

            ServerLog.LogError("");

            e.printStackTrace();
        }

       // serverSocket.bind(new InetSocketAddress(port));
        new Thread(this).start();
    }



    @Override
    public void run(){
        ServerLog.DebugLog("Server runing ip: " + serverSocket.getInetAddress() + " port: " + port);

        runnable = true;

        try {

            while(true) {
                Socket newClientConnection = serverSocket.accept();
                gameMenager.getMainFactory().newConnection(newClientConnection);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        return instance;
    }

    public AbstractGameMenager getGameMenager() {
        return gameMenager;
    }
}
