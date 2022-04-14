package me.ilucah.ahmaadsadventure.scenes.level.model;

import me.ilucah.ahmaadsadventure.handler.Handler;

import java.awt.*;

public abstract class Level {

    private static Level currentState = null;

    public static void setLevel(Level state) {
        currentState = state;
    }

    public static Level getLevel() {
        return currentState;
    }

    //CLASS

    protected Handler handler;

    public Level(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}