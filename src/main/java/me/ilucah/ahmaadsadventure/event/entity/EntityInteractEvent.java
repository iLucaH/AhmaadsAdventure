package me.ilucah.ahmaadsadventure.event.entity;

import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.event.Event;

public class EntityInteractEvent implements Event {

    private Class handle;
    private Entity interactingEntity;

    public EntityInteractEvent(Class handle, Entity interactingEntity) {
        this.handle = handle;
        this.interactingEntity = interactingEntity;
    }
    @Override
    public Class getHandle() {
        return handle;
    }

    public Entity getInteractingEntity() {
        return interactingEntity;
    }
}
