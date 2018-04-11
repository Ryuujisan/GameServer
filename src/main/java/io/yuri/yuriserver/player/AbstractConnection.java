package io.yuri.yuriserver.player;

import com.google.protobuf.InvalidProtocolBufferException;
import io.yuri.yuriserver.lobby.AbstractLobby;
import io.yuri.yuriserver.packet.OwnedPacket;
import io.yuri.yuriserver.packet.Protos;
import io.yuri.yuriserver.server.Server;
import io.yuri.yuriserver.utils.ServerLog;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AbstractConnection implements Runnable{

    public final int LENGHT_PACKET = 2;

    private Socket socket;
    private boolean isConnected = false;

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

        isConnected = true;

        new Thread(this).start();
    }

    public void setSocketConection(ISocketConection socketConection) {
        this.socketConection = socketConection;
    }

    public void send(Protos.Packet packet) throws IOException {

        try {

            Protos.Lenght leng = Protos.Lenght.newBuilder()
                    .setPacketLenght(packet.toByteArray().length)
                    .build();

            out.write(leng.toByteArray());
            out.write(packet.toByteArray());

        } catch (IOException e) {
            kick();
        }
    }

    private int encode() throws IOException {

        byte[] data = new byte[LENGHT_PACKET];

        for(int i = 0 ; i < data.length; i++) {

            try {
                data[i] = in.readByte();
            } catch (IOException e) {
                kick();
            }
        }

        try {
            Protos.Lenght lengt = Protos.Lenght.parseFrom(data);
            return lengt.getPacketLenght();

        } catch (InvalidProtocolBufferException e) {
            kick();
        }

        return 0;
    }


    private Protos.Packet getPacket(int packetLengt) throws IOException {

        byte[] data = new byte[packetLengt];

        for(int i = 0; i < packetLengt; i++) {
            try {
                data[i] = in.readByte();
            } catch (IOException e) {
                kick();
            }
        }

        return Protos.Packet.parseFrom(data);
    }


    @Override
    public void run() {

        try {

        while (isConnected) {
            Protos.Packet packet = getPacket(encode());
            OwnedPacket ownedPacket = new OwnedPacket(this, packet);

            ServerLog.DebugLog(packet.getChat().getText());

            Protos.Chat chat = Protos.Chat.newBuilder().setText("dsdsadsa").build();
            Protos.Packet test = Protos.Packet.newBuilder().setChat(chat).build();

            send(test);

            if(socketConection !=  null) {
                socketConection.onMessage(packet);
            } else {
                lobby.packetHandler.push(ownedPacket);
            }

        }
        } catch (Exception e) {

            try {
                kick();
            } catch (IOException e1) {

            }
        }
        try {
            kick();
        } catch (IOException e) {

        }
        ServerLog.DebugLog("Client Disconected: " + lobby.connectionList.size());
    }

    private void kick() throws IOException {
        isConnected = false;
        lobby.connectionList.remove((this));
        socket.close();
    }
}
