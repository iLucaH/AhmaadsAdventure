package me.ilucah.ahmaadsadventure.scenes.level;

import me.ilucah.ahmaadsadventure.handler.Handler;
import me.ilucah.ahmaadsadventure.scenes.level.implementation.Level1;
import me.ilucah.ahmaadsadventure.scenes.level.implementation.TutorialLevel;
import me.ilucah.ahmaadsadventure.scenes.level.model.LevelObject;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class LevelManager {

    private final CopyOnWriteArraySet<LevelObject> levelHandle;

    private LevelObject currentLevel;

    public LevelManager(Handler handler) {
        levelHandle = new CopyOnWriteArraySet<>();
        loadLevels(handler);
    }

    public void loadLevels(Handler handler) {
        levelHandle.add(new LevelObject(1F, new TutorialLevel(handler)));
        levelHandle.add(new LevelObject(2F, new Level1(handler)));
    }

    public LevelObject getLevel(float id) {
        return levelHandle.stream().filter(l -> l.getId() == id).findFirst().get();
    }
}
