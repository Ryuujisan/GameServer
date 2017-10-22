package io.yuri.yuriserver.lobby;

import Example.GameMenager;
import io.yuri.yuriserver.player.AbstractConnection;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractLobby {
    private final Timer timer = new Timer();

    public final HashMap<AbstractConnection, AbstractConnection> connectionList = new HashMap<AbstractConnection, AbstractConnection>();
    public final AbstracktLobbyPacketHendler packetHandler;


    public AbstractLobby() {
        packetHandler = GameMenager.getInstance().mainFactory.newLobbyPacketHendler(this);
    }

    public TimerTask newTimer(Runnable task, long delay){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay / 2, delay);

        return timerTask;
    }
}
