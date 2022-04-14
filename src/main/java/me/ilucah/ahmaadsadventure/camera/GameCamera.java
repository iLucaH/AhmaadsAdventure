package me.ilucah.ahmaadsadventure.camera;

import me.ilucah.ahmaadsadventure.entity.EntityManager;
import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.handler.Handler;

public class GameCamera {

    private Handler handler;
    private Entity focus;

    private int xOffset, yOffset;

    public GameCamera(Handler handler) {
        this.handler = handler;
        xOffset = 0;
        yOffset = 0;
    }

    public void setFocus(Entity focus) {
        this.focus = focus;
    }

    public void tick(EntityManager entityManager) {
        if (focus == null)
            return;
        for (Entity entity : entityManager.getEntities()) {
            if (entity == focus)
                continue;
            if (entity instanceof Player)
                continue;
            entity.setLocation(entity.getLocation().offset(xOffset, yOffset));
        }
        xOffset = 0;
        yOffset = 0;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void addXOffset(int amount) {
        xOffset += amount;
    }

    public void addYOffset(int amount) {
        yOffset += amount;
    }

    public void subtractXOffset(int amount) {
        xOffset -= amount;
    }

    public void subtractYOffset(int amount) {
        yOffset -= amount;
    }

}
