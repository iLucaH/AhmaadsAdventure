package me.ilucah.ahmaadsadventure.handler;

import me.ilucah.ahmaadsadventure.event.generic.model.EventBus;
import me.ilucah.ahmaadsadventure.input.KeyManager;
import me.ilucah.ahmaadsadventure.input.MouseManager;
import me.ilucah.ahmaadsadventure.level.LevelManager;

public class Handler {

    private Game game;
    private LevelManager levelManager;

    private final EventBus eventBus;

    public Handler(Game game){
        this.game = game;
        levelManager = new LevelManager(this);
        eventBus = new EventBus();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
