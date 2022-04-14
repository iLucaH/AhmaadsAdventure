package me.ilucah.ahmaadsadventure.entity.model;

public interface Movable {

    float getSpeed();
    float getJumpBoost();
    float getGravityValue();
    boolean isJumping();
    boolean isGrounded();
    boolean hasGravity();
    int getLeftVelocity();
    int getRightVelocity();
    int getJumpVelocity();

}
