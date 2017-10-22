package io.yuri.yuriserver.packet;

public class OwnedPacket<Owner> {
    public Owner owner;
    public io.yuri.yuriserver.packet.Protos.Packet packet;

    public OwnedPacket(Owner owner, io.yuri.yuriserver.packet.Protos.Packet data) {
        this.owner = owner;
        this.packet = data;
    }

}
