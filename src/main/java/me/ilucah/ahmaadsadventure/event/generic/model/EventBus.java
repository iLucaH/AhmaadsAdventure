package me.ilucah.ahmaadsadventure.event.generic.model;

import me.ilucah.ahmaadsadventure.event.generic.implementation.GenericInteractionEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EventBus {

    private final ConcurrentHashMap<Class, List<EventListener>> listeners = new ConcurrentHashMap<>();

    /**
     * @param event The event you want to call
     */
    public void submit(GenericEvent event) {
        Class clazz = event.getClass();
        if (!listeners.containsKey(clazz))
            return;
        listeners.get(clazz).stream().filter(h -> h != null).forEach(h -> h.handle(event));
    }

    /**
     * @param clazz The class of the event you are listening for.
     * @param listener The listener class.
     */
    public <T extends GenericEvent> void subscribe(Class<T> clazz, EventListener<T> listener) {
        if (!listeners.containsKey(clazz))
            listeners.put(clazz, new LinkedList<>());
        listeners.get(clazz).add(listener);
    }

    public Collection<EventListener> collectEvents(Class clazz) {
        return listeners.containsKey(clazz) ? listeners.get(clazz).stream().collect(Collectors.toList()) : Collections.EMPTY_LIST;
    }

    public <T extends GenericEvent> Optional<List<EventListener>> getHandlers(Class<T> clazz) {
        return Optional.ofNullable(listeners.get(clazz));
    }
}
