package io.yuri.yuriserver.lobby;

import io.yuri.yuriserver.packet.OwnedPacket;
import io.yuri.yuriserver.packet.Protos;
import io.yuri.yuriserver.player.AbstractConnection;
import io.yuri.yuriserver.utils.Config;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class AbstracktLobbyPacketHendler {

    private final Queue<OwnedPacket<AbstractConnection>> incoming = new LinkedBlockingDeque<>();
    public final AbstractLobby lobby;

    public AbstracktLobbyPacketHendler(AbstractLobby lobby) {
        this.lobby = lobby;
        lobby.newTimer(this::cycle, 1000 / Config.getInteger("packetHandlingRate"));
    }

    public void push(OwnedPacket<AbstractConnection> packet) throws IOException {
        if(packet.packet.hasPing()) {
            packet.owner.send(packet.packet);
        } else {
            incoming.add(packet);
        }
    }


    public void cycle() {
        while (!incoming.isEmpty()) {
                handle(incoming.remove());
        }
    }

    private void handle(OwnedPacket<AbstractConnection> packet) {
        if(packet.packet.hasJoinGame()) {
            joinGame(packet.owner, packet.packet.getJoinGame());
        }
    }

    protected abstract void handleCustom(OwnedPacket<AbstractConnection> message);

    private void joinGame(AbstractConnection lobbyPlayer, Protos.JoinGame joinGame) {
        //TODO
    }
}
