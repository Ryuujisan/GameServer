package Example;

import io.yuri.yuriserver.lobby.AbstractGameMenager;

public class GameMenager extends AbstractGameMenager {
    @Override
    protected void createMainClass() {
        mainFactory = new MainFactory();
        lobby = new Lobby();
    }
}
