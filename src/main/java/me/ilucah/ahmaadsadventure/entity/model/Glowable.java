package me.ilucah.ahmaadsadventure.entity.model;

import java.awt.*;

public interface Glowable {

    boolean isGlowing();

    void setGlowing(boolean val);

    Color getColor();

    void setColor(Color color);
}
