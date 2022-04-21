package me.ilucah.ahmaadsadventure.event.bounded.entity;

import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.event.bounded.BoundedEvent;

public class EntityInteractEvent implements BoundedEvent {

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
