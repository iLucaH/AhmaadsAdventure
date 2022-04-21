package me.ilucah.ahmaadsadventure.display.render;

import java.awt.*;
import java.util.function.Consumer;

public class RenderingObject {

    private final Consumer<Graphics> g;

    public float id;

    public RenderingObject(Consumer<Graphics> g) {
        this.g = g;
    }

    public RenderingObject(Consumer<Graphics> g, float id) {
        this.g = g;
        this.id = id;
    }

    public Consumer<Graphics> render() {
        return g;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }
}
