package me.ilucah.ahmaadsadventure.level.implementation;

import me.ilucah.ahmaadsadventure.display.render.RenderFactory;
import me.ilucah.ahmaadsadventure.entity.EntityManager;
import me.ilucah.ahmaadsadventure.entity.implementation.Barrier;
import me.ilucah.ahmaadsadventure.entity.implementation.Zombie;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.event.generic.implementation.LevelChangeEvent;
import me.ilucah.ahmaadsadventure.gfx.sound.SoundPlayer;
import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.level.model.Level;

import java.awt.*;

public class TutorialLevel extends Level {

    private final EntityManager entityManager;

    public TutorialLevel(Handler handler) {
        super(handler);
        entityManager = new EntityManager(handler);
        entityManager.addEntity(new Barrier(1000, 100, 0, 800));
        entityManager.addEntity(new Barrier(100, 100, 300, 600));
        entityManager.addEntity(new Zombie(100, 100, 1F));
    }

    @Override
    public void start() {

    }

    @Override
    public void tick() {
        if (handler.getKeyManager().right) {
            Level level = handler.getLevelManager().getLevel(2F).getLevel();
            LevelChangeEvent event = new LevelChangeEvent(Level.getLevel(), level);
            handler.getEventBus().submit(event);
            Level.setLevel(event.getNewLevel());
            SoundPlayer.playSound("audio/suspencful.wav");
        }
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
