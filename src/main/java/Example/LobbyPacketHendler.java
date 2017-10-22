package Example;

import io.yuri.yuriserver.lobby.AbstracktLobbyPacketHendler;
import io.yuri.yuriserver.lobby.AbstractLobby;
import io.yuri.yuriserver.packet.OwnedPacket;
import io.yuri.yuriserver.player.AbstractConnection;

public class LobbyPacketHendler extends AbstracktLobbyPacketHendler {

    public LobbyPacketHendler(AbstractLobby lobby) {
        super(lobby);
    }

    @Override
    protected void handleCustom(OwnedPacket<AbstractConnection> message) {

    }
}
