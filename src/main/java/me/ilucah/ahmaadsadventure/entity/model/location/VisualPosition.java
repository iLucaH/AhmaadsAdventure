package me.ilucah.ahmaadsadventure.entity.model.location;

public class VisualPosition {

    private float view;

    // 0 they are looking no where
    // -1 they are looking left
    // 1 they are looking right
    // the distance between -1 and 1 is the distance it can view.
    public VisualPosition(float view) {
        this.view = view;
    }

    public float getView() {
        return view;
    }

    public void setView(float view) {
        this.view = view;
    }
}
