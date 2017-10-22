package io.yuri.yuriserver.player;

import io.yuri.yuriserver.lobby.AbstractLobby;
import io.yuri.yuriserver.server.Server;
import io.yuri.yuriserver.utils.ServerLog;

import java.io.*;
import java.net.Socket;

public class AbstractConnection implements Runnable{
    private Socket socket;

    private ISocketConection socketConection;

    private AbstractLobby lobby;
    private DataOutputStream out;
    private DataInputStream in;

    public AbstractConnection(Socket socket) throws IOException {

        ServerLog.DebugLog("New Server Connection");

        this.socket = socket;
        lobby = Server.getInstance().getGameMenager().getLobby();

        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        lobby.connectionList.put(this, this);
        new Thread(this).start();
    }

    public void setSocketConection(ISocketConection socketConection) {
        this.socketConection = socketConection;
    }

    public void send(byte[] data) {

        try {
            out.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

        while (socket.isInputShutdown()) {
            // TODO:: jak podłącze proto

        }
            socket.close();
        lobby.connectionList.remove(this);
        } catch (Exception e) {
            try {
                socket.close();
                lobby.connectionList.remove(this);
            } catch (IOException e1) {

            }
            e.printStackTrace();
        }

        ServerLog.DebugLog("Client Disconected: " + lobby.connectionList.size());


    }
}
