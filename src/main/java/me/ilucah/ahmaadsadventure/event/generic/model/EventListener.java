package me.ilucah.ahmaadsadventure.event.generic.model;

/**
 * @param <T> The event class you want to listen for.
 */
public interface EventListener<T extends GenericEvent> {

    void handle(T event);

}
