package me.ilucah.ahmaadsadventure.level.model;

public class LevelObject {

    private float id;
    private Level level;

    public LevelObject(float id, Level level) {
        this.id = id;
        this.level = level;
    }

    public float getId() {
        return id;
    }

    public Level getLevel() {
        return level;
    }
}
