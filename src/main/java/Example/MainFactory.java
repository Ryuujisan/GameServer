package Example;

import Example.Player.Connection;
import io.yuri.yuriserver.lobby.*;
import io.yuri.yuriserver.lobby.AbstractRoomMenager;
import io.yuri.yuriserver.player.AbstractConnection;
import io.yuri.yuriserver.room.AbstractGame;
import io.yuri.yuriserver.room.AbstractRoom;

import java.io.IOException;
import java.net.Socket;

public class MainFactory extends AbstractMainFactory {
    @Override
    public AbstractConnection newConnection(Socket socket) throws IOException {
        return new Connection(socket);
    }

    @Override
    public AbstractPacketHandler newPacketHendler() {
        return null;
    }

    @Override
    public AbstractGame newGame(AbstractRoom room) {
        return null;
    }

    @Override
    public AbstractRoomMenager newRoomMenager() {
        return null;
    }

    @Override
    public AbstracktLobbyPacketHendler newLobbyPacketHendler(AbstractLobby lobby) {
        return new LobbyPacketHendler(lobby);
    }
}
