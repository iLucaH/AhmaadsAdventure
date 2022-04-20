package me.ilucah.ahmaadsadventure.scenes.level.implementation;

import me.ilucah.ahmaadsadventure.entity.EntityManager;
import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.scenes.level.model.Level;

import java.awt.*;

public class TutorialLevel extends Level {

    private final EntityManager entityManager;

    public TutorialLevel(Handler handler) {
        super(handler);
        entityManager = new EntityManager(handler);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
