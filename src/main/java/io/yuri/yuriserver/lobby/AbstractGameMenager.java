package io.yuri.yuriserver.lobby;

public abstract class AbstractGameMenager {
    private static AbstractGameMenager instance;
    protected AbstractRoomMenager roomMenager;
    protected AbstractMainFactory mainFactory;
    protected AbstractLobby lobby;


    public AbstractGameMenager() {
        setInstance(this);
    }

    public static void setInstance(AbstractGameMenager instance) {
        if(AbstractGameMenager.instance == null) {
            AbstractGameMenager.instance = instance;
            instance.createMainClass();
        }
    }

    public static AbstractGameMenager getInstance() {
        return instance;
    }


    protected abstract void createMainClass();

    public AbstractRoomMenager getRoomMenager() {
        return roomMenager;
    }

    public AbstractMainFactory getMainFactory() {
        return mainFactory;
    }

    public AbstractLobby getLobby() {
        return lobby;
    }
}
