package io.yuri.yuriserver.player;

import io.yuri.yuriserver.utils.Poolable;

public class AbstractPlayer implements Poolable {
    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
