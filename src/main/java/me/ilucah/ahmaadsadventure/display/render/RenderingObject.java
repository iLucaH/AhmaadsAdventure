package me.ilucah.ahmaadsadventure.display.render;

import java.awt.*;
import java.util.function.Consumer;

public class RenderingObject {

    private final Consumer<Graphics> g;

    public RenderingObject(Consumer<Graphics> g) {
        this.g = g;
    }

    public Consumer<Graphics> render() {
        return g;
    }
}
