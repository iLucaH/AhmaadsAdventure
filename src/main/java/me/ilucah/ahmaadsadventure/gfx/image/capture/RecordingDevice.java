package me.ilucah.ahmaadsadventure.gfx.image.capture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RecordingDevice {

    private final ConcurrentLinkedQueue<BufferedImage> images = new ConcurrentLinkedQueue<>();
    private final float sessionId;
    private final Rectangle rectangle;

    private boolean recording;
    private Thread thread;

    public RecordingDevice(float sessionId, Rectangle recordingBounds) {
        this.sessionId = sessionId;
        this.rectangle = recordingBounds;
    }

    public void submitFrame(BufferedImage image) {
        images.add(image);
    }

    public void renderGraphics() {
        // compile here
    }

    public void build(File outputFile) {
        // save to file here
    }

    public synchronized Rectangle getRecordingBounds() {
        return rectangle;
    }

    public synchronized void start(Thread thread) {
        recording = true;
        this.thread = thread;
        this.thread.start();
    }

    public synchronized void stop() {
        if (recording == false)
            return;
        recording = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean isRecording() {
        return recording;
    }
}
