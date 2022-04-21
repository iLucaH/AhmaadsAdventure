package me.ilucah.ahmaadsadventure.event.generic.model;

public interface CancellableEvent {

    boolean isCancelled();

    void setCancelled(boolean val);
}
