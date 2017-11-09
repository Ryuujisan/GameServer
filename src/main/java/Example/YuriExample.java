package Example;

import io.yuri.yuriserver.server.Server;
import io.yuri.yuriserver.packet.Protos;

public class YuriExample {

    public static void main(String args[]) {
        try {
            new Server(new GameMenager(), (short) 6969);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
