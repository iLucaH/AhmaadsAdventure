package me.ilucah.ahmaadsadventure.scenes.implementation;

import me.ilucah.ahmaadsadventure.camera.GameCamera;
import me.ilucah.ahmaadsadventure.display.render.RenderFactory;
import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.scenes.Scene;
import me.ilucah.ahmaadsadventure.scenes.level.model.Level;

import java.awt.*;

public class GameScene extends Scene {

    private Player player;
    private GameCamera camera;

    public GameScene(Handler handler) {
        super(handler);
        Level.setLevel(handler.getLevelManager().getLevel(1F).getLevel());
        camera = new GameCamera(handler);
        initEntities();
        camera.setFocus(player);
    }

    private void initEntities() {
        this.player = new Player(handler, camera);
        Level.getLevel().getEntityManager().addEntity(player);
    }

    @Override
    public void tick() {
        Level.getLevel().tick();
        camera.tick(Level.getLevel().getEntityManager());
    }

    @Override
    public void render(Graphics g) {
        Level.getLevel().render(g);
    }
}