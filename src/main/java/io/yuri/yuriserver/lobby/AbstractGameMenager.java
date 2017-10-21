package io.yuri.yuriserver.lobby;

public abstract class AbstractGameMenager {
    private static AbstractGameMenager instance;
    protected AbstractRoomMenager roomMenager;
    protected AbstractMainFactory mainFactory;
    protected AbstractLobby       abstractLobby;


    public static void setInstance(AbstractGameMenager instance) {
        if(AbstractGameMenager.instance == null) {
            AbstractGameMenager.instance = instance;
        }
    }

    public static AbstractGameMenager getInstance() {
        return instance;
    }


    public abstract void createMainClass();

    public AbstractRoomMenager getRoomMenager() {
        return roomMenager;
    }

    public AbstractMainFactory getMainFactory() {
        return mainFactory;
    }

    public AbstractLobby getAbstractLobby() {
        return abstractLobby;
    }
}
