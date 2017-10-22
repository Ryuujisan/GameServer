package io.yuri.yuriserver.lobby;

import io.yuri.yuriserver.player.AbstractConnection;
import io.yuri.yuriserver.room.AbstractGame;
import io.yuri.yuriserver.room.AbstractRoom;

import java.io.IOException;
import java.net.Socket;

public abstract class AbstractMainFactory {
    private static AbstractMainFactory instance;

    public static void setInstance(AbstractMainFactory instance) {
        AbstractMainFactory.instance = instance;
    }

    public static AbstractMainFactory getInstance() {
        return instance;
    }

    public abstract AbstractConnection newConnection(Socket socket) throws IOException;
    public abstract AbstractPacketHandler newPacketHendler();
    public abstract AbstractGame newGame(AbstractRoom room);


}
