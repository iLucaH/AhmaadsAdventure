package me.ilucah.ahmaadsadventure.entity.implementation;

import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.entity.model.location.VisualPosition;
import me.ilucah.ahmaadsadventure.event.bounded.entity.EntityInteractEvent;
import me.ilucah.ahmaadsadventure.level.model.Level;

import java.awt.*;

public class Zombie extends Entity {

    private final VisualPosition positionalView;

    private float maxViewDistance = 325;

    private Entity entityFocus;

    public Zombie(int locX, int locZ, float viewPosition) {
        super(100, 100, locX, locZ);
        positionalView = new VisualPosition(viewPosition);
    }

    @Override
    public void tick() {
        float view = maxViewDistance * positionalView.getView();
        if (entityFocus == null) {
            searchEntities();
            return;
        }

    }

    @Override
    public void render(Graphics g) {
        g.fillRect(getLocX(), getLocZ(), 100, 100);
    }

    @Override
    public void onInteract(EntityInteractEvent que) {

    }

    public VisualPosition getPositionalView() {
        return positionalView;
    }

    public void setEntityFocus(Entity entity) {
        this.entityFocus = entity;
    }

    public Entity getFocusedEntity() {
        return entityFocus;
    }

    public void searchEntities() {
        float view = maxViewDistance * positionalView.getView();
        for (Entity entity : Level.getLevel().getEntityManager().getEntities()) {
            if (!(entity instanceof Player))
                continue;
        }
    }
}
