package me.ilucah.ahmaadsadventure.gfx.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
            String path = url;

            public void run() {
                try {
                    URL url = this.getClass().getClassLoader().getResource(path);
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
