package me.ilucah.ahmaadsadventure.scenes.level.model;

import me.ilucah.ahmaadsadventure.entity.EntityManager;
import me.ilucah.ahmaadsadventure.handler.Handler;

import java.awt.Graphics;

public abstract class Level {

    private static Level currentState = null;

    public static void setLevel(Level state) {
        state.start();
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

    public abstract void start();

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract EntityManager getEntityManager();
}