package io.yuri.yuriserver.lobby;

import io.yuri.yuriserver.player.AbstractConnection;

import java.util.HashMap;

public abstract class AbstractLobby {
    public final HashMap<AbstractConnection, AbstractConnection> connectionList = new HashMap<AbstractConnection, AbstractConnection>();


}
