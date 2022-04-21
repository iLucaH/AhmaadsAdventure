package me.ilucah.ahmaadsadventure.gfx.image.capture;

import me.ilucah.ahmaadsadventure.handler.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class CaptureDirectory {

    private static CaptureDirectory instance;

    public static CaptureDirectory getInstance() {
        return instance;
    }

    private final ExecutorService threadPoolExecutor = new ThreadPoolExecutor(50, 50,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());
    private final Robot robot = new Robot();
    private final ConcurrentHashMap<Float, RecordingDevice> recordingDevices = new ConcurrentHashMap<>();

    public CaptureDirectory() throws AWTException {
        instance = this;
    }

    public void captureMoment(Rectangle rectangle) {
        BufferedImage image = robot.createScreenCapture(rectangle);
    }

    public void captureMoment() {
        captureMoment(Game.getGame().getDisplay().getCanvas().getBounds());
    }

    public BufferedImage[] captureMoments(Rectangle rectangle, int moments, int distance, TimeUnit unit) {
        if (moments < 1)
            return new BufferedImage[0];
        BufferedImage[] ar = new BufferedImage[moments];
        new Thread(() -> {
            for (int i = 0; i < moments; i++) {
                ar[i] = robot.createScreenCapture(rectangle);
                try {
                    Thread.sleep(unit.convert(distance, unit));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return ar;
    }

    public BufferedImage[] captureMoments(Rectangle rectangle, int moments, int distance) {
        return captureMoments(rectangle, moments, distance, TimeUnit.MILLISECONDS);
    }

    public BufferedImage[] captureMoments(Rectangle rectangle, int moments) {
        return captureMoments(rectangle, moments, 10000, TimeUnit.MILLISECONDS);
    }

    public BufferedImage[] captureMoments(int moments) {
        return captureMoments(Game.getGame().getDisplay().getCanvas().getBounds(), moments, 10000, TimeUnit.MILLISECONDS);
    }

    public BufferedImage scheduleDelayedSynchronousMoment(Rectangle bounds, int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return robot.createScreenCapture(bounds);
    }

    public BufferedImage scheduleDelayedAsynchronousMoment(Rectangle bounds, int delay) {
        AtomicReference<BufferedImage> image = new AtomicReference<>();
        new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            image.set(robot.createScreenCapture(bounds));
        }).start();
        return image.get();
    }

    /**
     * Needs attention. Just the loop
     *
     * @param id              recording id.
     * @param recordingBounds screen capture bounds.
     * @author iLucaH
     */
    public void startRecording(float id, Rectangle recordingBounds) {
        Optional<RecordingDevice> oD = Optional.ofNullable(getRecordingDevice(id));
        if (!oD.isPresent())
            throw new IllegalArgumentException("Unknown recording id: " + id + "!");
        RecordingDevice rD = oD.get();
        Thread thread = new Thread(() -> {
            int fps = 60;
            double timePerTick = 1000000000 / fps;
            double delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer = 0;
            while (rD.isRecording()) {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;
                if (delta >= 1) {
                    threadPoolExecutor.submit(() -> rD.submitFrame(robot.createScreenCapture(rD.getRecordingBounds())));
                    delta--;
                }
                if (timer >= 1000000000) {
                    timer = 0;
                }
            }
        });
        rD.start(thread);
    }

    public float createRecordingDevice(Rectangle recordingBounds) {
        float f = recordingDevices.size() + 1;
        recordingDevices.put(f, new RecordingDevice(f, recordingBounds));
        return f;
    }

    public ExecutorService getThreadPoolExecutorService() {
        return threadPoolExecutor;
    }

    public Robot getRobot() {
        return robot;
    }

    public Stream<RecordingDevice> getRecordingDevices() {
        return recordingDevices.values().stream();
    }

    public RecordingDevice getRecordingDevice(float id) {
        return recordingDevices.get(id);
    }
}
