package io.yuri.yuriserver.room;

import io.yuri.yuriserver.lobby.AbstractMainFactory;
import io.yuri.yuriserver.lobby.AbstractPacketHandler;
import io.yuri.yuriserver.lobby.AbstractRoomMenager;
import io.yuri.yuriserver.player.AbstractPlayer;
import io.yuri.yuriserver.utils.Pool;
import io.yuri.yuriserver.utils.Poolable;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractRoom implements Poolable {

    private int id;
    private final Timer timer = new Timer();
    private AbstractGame game;
    private  boolean locket;

    public final Pool<AbstractPlayer> players = new Pool<>();

    private final AbstractPacketHandler packetHandler;
    private final AbstractRoomMenager roomMenager;

    public AbstractRoom(AbstractRoomMenager roomMenager) {
        this.roomMenager = roomMenager;
        packetHandler = AbstractMainFactory.getInstance().newPacketHendler();
    }
    //TODO: jak bede garniał dodac metody do dolaczenia i opuszcznia gracza i wysyłania pakietu


    public TimerTask newTimer(Runnable task, long delay) {

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

        timer.scheduleAtFixedRate(timerTask, delay /2, delay);
        return  timerTask;
    }

    public TimerTask newTimeout(Runnable task, long delay){
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
        timer.schedule(timerTask, delay);

        return timerTask;
    }


    public boolean starGame() {
        if(game == null) {
            game = AbstractMainFactory.getInstance().newGame(this);
            game.start();
            return true;
        } else {
            return false;
        }
    }

    public boolean endGame() {
        if(game == null) {
            return false;
        } else {
            game.end();
            game = null;
            return  false;
        }
    }

    //todo wysyłanie paietaów


    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocket() {
        return locket;
    }

    public void setLocket(boolean locket) {
        this.locket = locket;
    }

    //TODO zrobic tworzenie sie pakietow prot player join tp;
}
