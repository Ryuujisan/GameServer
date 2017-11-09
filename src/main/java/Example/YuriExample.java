package Example;

import io.yuri.yuriserver.server.Server;
import io.yuri.yuriserver.packet.Protos;

public class YuriExample {

    public static void main(String args[]) {

        Protos.Lenght lenght = Protos.Lenght.newBuilder().setPacketLenght(0).build();
      //  System.out.println("Długosc: " + lenght.toByteArray().length);


        try {
            new Server(new GameMenager(), (short) 6969);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
