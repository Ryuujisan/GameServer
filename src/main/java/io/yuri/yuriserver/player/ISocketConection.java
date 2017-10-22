package io.yuri.yuriserver.player;

import io.yuri.yuriserver.packet.Protos;

public interface ISocketConection {

     void onMessage(Protos.Packet packet);
     void onError(String resson);

}
