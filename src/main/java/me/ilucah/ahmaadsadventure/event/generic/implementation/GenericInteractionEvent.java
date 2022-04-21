package me.ilucah.ahmaadsadventure.event.generic.implementation;

import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.entity.model.Entity;
import me.ilucah.ahmaadsadventure.entity.model.location.Location;
import me.ilucah.ahmaadsadventure.event.generic.model.GenericEvent;

public class GenericInteractionEvent implements GenericEvent {

    private final Player player;
    private final Entity interactingEntity;
    private final Location location;

    public GenericInteractionEvent(Player player, Entity interactingEntity, Location location) {
        this.player = player;
        this.interactingEntity = interactingEntity;
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getInteractingEntity() {
        return interactingEntity;
    }

    public Location getLocation() {
        return location;
    }
}
