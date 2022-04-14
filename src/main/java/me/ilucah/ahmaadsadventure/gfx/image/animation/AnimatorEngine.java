package me.ilucah.ahmaadsadventure.gfx.image.animation;

import java.util.concurrent.ConcurrentHashMap;

public class AnimatorEngine {

    private ConcurrentHashMap<Integer, Animation> animations;
    private int currentFrameId;

    public AnimatorEngine() {
        this.animations = new ConcurrentHashMap<>();
    }

    public AnimatorEngine addFrame(AnimationFactory handle) {
        animations.put(handle.id(), handle.handle());
        return this;
    }

    public AnimatorEngine setCurrentAnimation(int animationId) {
        currentFrameId = animationId;
        return this;
    }

    public Animation getCurrentAnimation() {
        return animations.get(currentFrameId);
    }

    public void tickFrames() {
        animations.get(currentFrameId).tick();
    }
}
