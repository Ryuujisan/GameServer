package io.yuri.yuriserver.utils;

/*
    Basic proto utils Converting Data to protoPacket ready to Send ;-)
    extend this class to yours packet
 */


import io.yuri.yuriserver.packet.Protos;
import io.yuri.yuriserver.player.AbstractPlayer;
import io.yuri.yuriserver.room.AbstractRoom;

public class AbstractPacketFactory {

    public static Protos.Packet room(AbstractRoom room, AbstractPlayer player) {
        return Protos.Packet.newBuilder()
                .setRoom(room.roomProto(player))
                .build();
    }


}
