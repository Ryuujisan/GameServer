package io.yuri.yuriserver.player;

import io.yuri.yuriserver.packet.Protos;
import io.yuri.yuriserver.room.AbstractRoom;
import io.yuri.yuriserver.utils.Poolable;

import java.util.ArrayList;
import java.util.List;

public class AbstractPlayer implements Poolable, ISocketConection {
    private Integer id;
    private AbstractConnection connection;
    private AbstractRoom room;
    private List<Protos.Update.Event> events = new ArrayList<>();

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public AbstractPlayer(AbstractConnection connection, AbstractRoom room) {
        this.connection = connection;
        this.room = room;
        connection.setSocketConection(this);
    }


    public void send(Protos.Packet packet) {
        connection.send(packet);
    }

    @Override
    public void onMessage(Protos.Packet packet) {


    }

    @Override
    public void onError(String resson) {

    }

    public Integer getId() {
        return id;
    }

    public AbstractConnection getConnection() {
        return connection;
    }

    public AbstractRoom getRoom() {
        return room;
    }

    public List<Protos.Update.Event> getEvents() {
        return events;
    }
}
