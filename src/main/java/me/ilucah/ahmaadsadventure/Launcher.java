package me.ilucah.ahmaadsadventure;

import me.ilucah.ahmaadsadventure.gfx.sound.SoundPlayer;
import me.ilucah.ahmaadsadventure.handler.Game;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Ahmaad's Adventure", 1980, 1080);
        SoundPlayer.playSound("mainsound.wav");
        game.start();
    }
}
