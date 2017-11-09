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

    public void send(Protos.Packet packet) {

        try {

            Protos.Lenght leng = Protos.Lenght.newBuilder()
                    .setPacketLenght(packet.toByteArray().length)
                    .build();

            out.write(leng.toByteArray());
            out.write(packet.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int encode() {

        byte[] data = new byte[LENGHT_PACKET];

        for(int i = 0 ; i < data.length; i++) {

            try {
                data[i] = in.readByte();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Protos.Lenght lengt = Protos.Lenght.parseFrom(data);
            return lengt.getPacketLenght();

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        return 0;
    }


    private Protos.Packet getPacket(int packetLengt) throws InvalidProtocolBufferException {

        byte[] data = new byte[packetLengt];

        for(int i = 0; i < packetLengt; i++) {
            try {
                data[i] = in.readByte();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Protos.Packet.parseFrom(data);
    }


    @Override
    public void run() {

        try {

        while (socket.isConnected()) {
            Protos.Packet packet = getPacket(encode());
            OwnedPacket ownedPacket = new OwnedPacket(this, packet);

            if(socketConection !=  null) {
                socketConection.onMessage(packet);
            } else {
                lobby.packetHandler.push(ownedPacket);
            }

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
