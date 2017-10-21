package io.yuri.yuriserver.room;

import io.yuri.yuriserver.player.AbstractPlayer;
import io.yuri.yuriserver.utils.Config;

import java.util.TimerTask;

public abstract class AbstractGame {
    public AbstractRoom room;
    private TimerTask simulation;
    private TimerTask updater;
    private long lastSimulate = System.currentTimeMillis();


    public AbstractGame(AbstractRoom room) {
        this.room = room;
        simulation = room.newTimer(this::simulate, 1000 / Config.getInteger("simulationRate"));
        updater = room.newTimer(this::sendEvents, 1000 / Config.getInteger("updateRate"));
    }

    private void simulate() {
        float dt = (System.currentTimeMillis() - lastSimulate) / 1000.0f;
        lastSimulate = System.currentTimeMillis();
        onSimulate(dt);
    }

    protected abstract void onSimulate(float dt);

    public void start() {
        //TODO
    }

    protected abstract void onStart();

    public void end() {
        simulation.cancel();
        updater.cancel();
        onEnd();
        //TODO wysłanie pakietu pozniej
    }

    protected abstract void onEnd();

    // dodac zmienna z proto puzniej
    public void addEvent(EventFilter filter) {
        for(AbstractPlayer player : room.players) {
            if(filter.visibleFor(player)){
                //TODO dodanie eventu dla gracza;
            }
        }
    }

    private void sendEvents() {

    }


    //TODO proto

    public interface EventFilter {
        EventFilter everyone = player -> true;
        EventFilter none = player -> false;
        boolean visibleFor(AbstractPlayer player);
    }
}
