package me.ilucah.ahmaadsadventure.event.generic.implementation;

import me.ilucah.ahmaadsadventure.event.generic.model.CancellableEvent;
import me.ilucah.ahmaadsadventure.event.generic.model.GenericEvent;
import me.ilucah.ahmaadsadventure.event.generic.model.RepetitiveEvent;
import me.ilucah.ahmaadsadventure.scenes.level.model.Level;

public class LevelChangeEvent implements GenericEvent, CancellableEvent, RepetitiveEvent {

    private final Level oldLevel;
    private Level newLevel;

    private boolean isCancelled;

    public LevelChangeEvent(Level oldLevel, Level newLevel) {
        this.oldLevel = oldLevel;
        this.newLevel = newLevel;
    }

    public Level getOldLevel() {
        return oldLevel;
    }

    public Level getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(Level newLevel) {
        this.newLevel = newLevel;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean val) {
        isCancelled = val;
    }

    @Override
    public void loop(int amount) {
        for (int i = 0; i < amount; i++) {
            Level.setLevel(newLevel);
        }
    }
}
