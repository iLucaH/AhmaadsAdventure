package me.ilucah.ahmaadsadventure.scenes.implementation;

import me.ilucah.ahmaadsadventure.camera.GameCamera;
import me.ilucah.ahmaadsadventure.entity.implementation.Barrier;
import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.scenes.Scene;
import me.ilucah.ahmaadsadventure.scenes.level.model.Level;

import java.awt.*;

public class GameScene extends Scene {

    private float x, y;
    private Player player;
    private GameCamera camera;

    public GameScene(Handler handler) {
        super(handler);
        this.x = 300;
        this.y = 300;
        camera = new GameCamera(handler);
        initEntities();
        camera.setFocus(player);
        Level.setLevel(handler.getLevelManager().getLevel(1F).getLevel());
    }

    private void initEntities() {
        this.player = new Player(handler, camera);
        Level.getLevel().getEntityManager().addEntity(player);
        Level.getLevel().getEntityManager().addEntity(new Barrier(1000, 100, 0, 800));
        Level.getLevel().getEntityManager().addEntity(new Barrier(100, 100, 300, 600));
    }

    @Override
    public void tick() {
        Level.getLevel().tick();
        for (Entity entity : Level.getLevel().getEntityManager().getEntities()) {
            entity.tick();
        }
        camera.tick(Level.getLevel().getEntityManager());
    }

    @Override
    public void render(Graphics g) {
        Level.getLevel().render(g);
        for (Entity entity : Level.getLevel().getEntityManager().getEntities()) {
            entity.render(g);
        }
    }
}