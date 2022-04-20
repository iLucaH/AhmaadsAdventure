package me.ilucah.ahmaadsadventure.display.render;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class RenderFactory {

    private static ArrayList<RenderingObject> renders = new ArrayList<>();

    public static void clear() {
        renders.clear();
    }

    public static void add(RenderingObject object) {
        renders.add(object);
    }

    public static void remove(RenderingObject object) {
        renders.remove(object);
    }

    public static Stream<RenderingObject> getRenders() {
        return renders.stream();
    }

    public static Optional<RenderingObject> seek(Consumer<Graphics> g) {
        return renders.stream().filter(r -> r != null).filter(r -> r.render() == g).findAny();
    }
}
