package io.yuri.yuriserver.player;

public interface ISocketConection {

     void onMessage(byte[] data);
     void onError(String resson);

}
