package me.ilucah.ahmaadsadventure.entity.implementation;

import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.entity.model.Glowable;
import me.ilucah.ahmaadsadventure.entity.model.Solidified;
import me.ilucah.ahmaadsadventure.event.bounded.entity.EntityInteractEvent;

import java.awt.*;

public class Barrier extends Entity implements Solidified, Glowable {

    private Color color;
    private boolean isGlowing;
    private boolean isVisible;

    public Barrier(int width, int height, int x, int y) {
        super(width, height, x, y);
        color = Color.BLACK;
        isGlowing = true;
        isVisible = true;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if (!isVisible)
            return;
        g.setColor(isGlowing ? color : Color.BLACK);
        g.drawRect(getLocX(), getLocZ(), getWidth(), getHeight());
        g.setColor(Color.BLACK);
    }

    @Override
    public void onInteract(EntityInteractEvent que) {}

    @Override
    public String getName() {
        return "Barrier";
    }

    @Override
    public float getId() {
        return 0;
    }

    @Override
    public boolean isGlowing() {
        return isGlowing;
    }

    @Override
    public void setGlowing(boolean val) {
        this.isGlowing = val;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean val) {
        isVisible = val;
    }
}
