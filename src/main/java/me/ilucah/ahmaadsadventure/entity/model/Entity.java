package me.ilucah.ahmaadsadventure.entity.model;

import me.ilucah.ahmaadsadventure.entity.model.location.Location;
import me.ilucah.ahmaadsadventure.event.entity.EntityInteractEvent;

import java.awt.*;

public abstract class Entity {

    private int width, height, locX, locZ;
    private Rectangle bounds;

    public Entity(int width, int height, int locX, int locZ) {
        this.width = width;
        this.height = height;
        this.locX = locX;
        this.locZ = locZ;
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocZ() {
        return locZ;
    }

    public void setLocX(int newLocX) {
        locX = newLocX;
        bounds.setLocation(locX, locZ);
    }

    public void setLocZ(int newLocZ) {
        locZ = newLocZ;
        bounds.setLocation(locX, locZ);
    }

    public void setLocation(Location location) {
        locX = location.getLocX();
        locZ = location.getLocY();
        bounds.setLocation(locX, locZ);
    }

    public Location getLocation() {
        return new Location(locX, locZ);
    }

    public Rectangle getCollisionBounds() {
        return new Rectangle(locX, locZ, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onInteract(EntityInteractEvent que);
}
