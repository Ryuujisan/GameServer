package io.yuri.yuriserver.lobby;

import io.yuri.yuriserver.room.AbstractGame;
import io.yuri.yuriserver.room.AbstractRoom;

public abstract class AbstractMainFactory {
    private static AbstractMainFactory instance;

    public static void setInstance(AbstractMainFactory instance) {
        AbstractMainFactory.instance = instance;
    }

    public static AbstractMainFactory getInstance() {
        return instance;
    }

    public abstract AbstractPacketHandler newPacketHendler();
    public abstract AbstractGame newGame(AbstractRoom room);


}
