package me.ilucah.ahmaadsadventure.scenes.level.implementation;

import me.ilucah.ahmaadsadventure.display.render.RenderFactory;
import me.ilucah.ahmaadsadventure.entity.EntityManager;
import me.ilucah.ahmaadsadventure.entity.implementation.Barrier;
import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.entity.implementation.Zombie;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.entity.model.location.Location;
import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.scenes.level.model.Level;

import java.awt.*;

public class Level1 extends Level {

    private final EntityManager entityManager;

    public Level1(Handler handler) {
        super(handler);

        entityManager = new EntityManager(handler);

        entityManager.addEntity(new Barrier(500, 500, 100, 500));
        entityManager.addEntity(new Barrier(100, 100, 300, 200));
        entityManager.addEntity(new Zombie(100, 100, 1F));
    }

    @Override
    public void start() {
        Player player = handler.getGame().getGameScene().getPlayer();
        entityManager.addEntity(player);
        player.setOffsetLocation(new Location(400, 510));
    }

    @Override
    public void tick() {
        for (Entity entity : entityManager.getEntities()) {
            RenderFactory.getThreadPool().submit(() -> entity.tick());
        }
    }

    @Override
    public void render(Graphics g) {
        for (Entity entity : entityManager.getEntities()) {
            RenderFactory.getThreadPool().submit(() -> entity.render(g));
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
