package me.ilucah.ahmaadsadventure.entity.implementation;

import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.event.entity.EntityInteractEvent;

import java.awt.*;

public class Zombie extends Entity {

    public Zombie(int locX, int locZ) {
        super(100, 100, locX, locZ);
    }

    @Override
    public void tick() {
        setLocX(getLocX() + 2);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(getLocX(), getLocZ(), 100, 100);
    }

    @Override
    public void onInteract(EntityInteractEvent que) {

    }
}
