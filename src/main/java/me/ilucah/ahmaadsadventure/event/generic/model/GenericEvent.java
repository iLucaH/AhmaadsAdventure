package me.ilucah.ahmaadsadventure.event.generic.model;

import me.ilucah.ahmaadsadventure.event.Events;

import java.util.List;
import java.util.Optional;

public interface GenericEvent {

    default <T extends EventListener> Optional<List<EventListener>> getHandlers() {
        return Events.getHandle().getHandlers(this.getClass());
    }

}
