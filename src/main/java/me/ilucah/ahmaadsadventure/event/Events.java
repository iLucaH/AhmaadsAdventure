package me.ilucah.ahmaadsadventure.event;

import me.ilucah.ahmaadsadventure.event.generic.model.EventBus;
import me.ilucah.ahmaadsadventure.event.generic.model.EventListener;
import me.ilucah.ahmaadsadventure.event.generic.model.GenericEvent;
import me.ilucah.ahmaadsadventure.handler.Game;

public class Events {

    public static <T extends GenericEvent> void registerEvent(Class<T> clazz, EventListener<T> listener) {
        Game.getGame().getHandler().getEventBus().subscribe(clazz, listener);
    }

    public void callEvent(GenericEvent event) {
        Game.getGame().getHandler().getEventBus().submit(event);
    }

    public static EventBus getHandle() {
        return Game.getGame().getHandler().getEventBus();
    }

}
