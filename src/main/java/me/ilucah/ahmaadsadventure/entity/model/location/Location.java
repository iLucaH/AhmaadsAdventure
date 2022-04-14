package me.ilucah.ahmaadsadventure.entity.model.location;

public class Location {

    private int x, z;

    public Location(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public int getLocX() {
        return x;
    }

    public int getLocY() {
        return z;
    }

    public Location offset(int x, int y) {
        return new Location(this.x + x, this.z + y);
    }

}
