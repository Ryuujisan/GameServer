package io.yuri.yuriserver.player;

import io.yuri.yuriserver.packet.Protos;
import io.yuri.yuriserver.utils.Poolable;

public class AbstractPlayer implements Poolable, ISocketConection {
    private int id;
    private AbstractConnection connection;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    public AbstractPlayer(AbstractConnection connection) {
        this.connection = connection;
        connection.setSocketConection(this);
    }

    @Override
    public void onMessage(Protos.Packet packet) {

    }

    @Override
    public void onError(String resson) {

    }
}
