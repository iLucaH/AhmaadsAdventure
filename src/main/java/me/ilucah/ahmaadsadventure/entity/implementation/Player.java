package me.ilucah.ahmaadsadventure.entity.implementation;

import me.ilucah.ahmaadsadventure.camera.GameCamera;
import me.ilucah.ahmaadsadventure.entity.model.*;
import me.ilucah.ahmaadsadventure.entity.model.inventory.Inventory;
import me.ilucah.ahmaadsadventure.entity.model.inventory.InventoryFactory;
import me.ilucah.ahmaadsadventure.event.entity.EntityInteractEvent;
import me.ilucah.ahmaadsadventure.gfx.image.PathedImage;
import me.ilucah.ahmaadsadventure.gfx.image.animation.Animation;
import me.ilucah.ahmaadsadventure.gfx.image.animation.AnimationFactory;
import me.ilucah.ahmaadsadventure.gfx.image.animation.AnimatorEngine;
import me.ilucah.ahmaadsadventure.gfx.objects.InventoryPanel;
import me.ilucah.ahmaadsadventure.handler.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class Player extends Entity implements Movable, InventoryHolder {

    private Handler handler;
    private float speed, jumpBoost, gravity;
    private boolean jumping;
    private boolean hasGravity, isOnGround;
    private int jumpVelocity, rightVelocity, leftVelocity, standardVelocity, floorDistance;
    private double fallVelocity;
    private int[] velocityParset;

    private Inventory inventory;
    private InventoryPanel renderingInventory;

    private GameCamera camera;

    private AnimatorEngine animator;

    public Player(Handler handler, GameCamera camera) {
        super(100, 100, (handler.getWidth() / 2) - 100, (handler.getHeight() / 2) - 100);
        this.handler = handler;
        this.camera = camera;
        speed = 10;
        jumpBoost = 55;
        hasGravity = true;
        isOnGround = false;
        gravity = 10;
        standardVelocity = 100;
        jumpVelocity = 0;
        rightVelocity = 0;
        leftVelocity = 0;
        floorDistance = 0;
        fallVelocity = 0;
        velocityParset = new int[3];
        velocityParset[0] = standardVelocity / 3;
        velocityParset[1] = (standardVelocity / 3) * 2;
        velocityParset[2] = (standardVelocity / 3) * 3;
        standardVelocity = velocityParset[2];
        initAnimations();

        this.inventory = new Inventory(this);
        this.renderingInventory = null;
    }

    private void initAnimations() {
        animator = new AnimatorEngine().addFrame(new AnimationFactory() {

            // idle

            @Override
            public Animation handle() {
                PathedImage[] frames = new PathedImage[]{new PathedImage("0.png")};
                return new Animation(1, frames);
            }

            @Override
            public int id() {
                return 0;
            }

        }).setCurrentAnimation(0);
    }

    @Override
    public void tick() {
        boolean isColliding = (handler.getGame().getGameScene().getEntityManager().getEntities().stream().filter(e -> {
            if (!(e instanceof Solidified))
                return false;
            return (e.getCollisionBounds().intersects(getMovementCollisionBounds()));
        }).findFirst().isPresent());
        if (handler.getKeyManager().up) {
            if (jumping == false && isOnGround && hasGravity) {
                //setLocZ(getLocZ() - (int) speed);
                camera.addYOffset((int) speed);
                jumping = true;
                jumpVelocity = (int) jumpBoost;
                isOnGround = false;
            }
        }
        if (handler.getKeyManager().left) {
            if (!isColliding)
                camera.addXOffset((int) speed);
        }
        if (handler.getKeyManager().right) {
            if (!isColliding)
                camera.subtractXOffset((int) speed);
        }
        if (handler.getKeyManager().keyJustReleased(KeyEvent.VK_D))
            rightVelocity = 25;
        if (rightVelocity > 0) {
            //setLocX(getLocX() + (int) (rightVelocity / gravity));
            if (!isColliding) {
                camera.subtractXOffset((int) (rightVelocity / gravity));
                rightVelocity--;
            }
        }
        if (handler.getKeyManager().keyJustReleased(KeyEvent.VK_A))
            leftVelocity = 25;
        if (leftVelocity > 0) {
            //setLocX(getLocX() - (int) (leftVelocity /
            if (!isColliding) {
                camera.addXOffset((int) (leftVelocity / gravity));
                leftVelocity--;
            }
        }
        // physics
        if (hasGravity) {
            if (jumping == false) {
                Optional<Entity> floor = (handler.getGame().getGameScene().getEntityManager().getEntities().stream().filter(e -> {
                    if (!(e instanceof Solidified))
                        return false;
                    if (e.getCollisionBounds().intersects(getCollisionBounds())) {
                        isOnGround = true;
                        fallVelocity = 0;
                        floorDistance = (int) e.getCollisionBounds().getY();
                        onInteract(new EntityInteractEvent(this.getClass(), e));
                        return true;
                    }
                    floorDistance = (int) e.getCollisionBounds().getY();
                    isOnGround = false;
                    return false;
                }).findFirst());
                if (floor.isPresent() == false) {
                    Rectangle newBounds = new Rectangle(getMovementCollisionBounds());
                    newBounds.add(0, 1 + fallVelocity);
                    if (handler.getGame().getGameScene().getEntityManager().getEntities().stream().filter(e -> {
                        return (e.getCollisionBounds().intersects(newBounds));
                    }).findFirst().isPresent()) {
                        if (isOnGround == false)
                            camera.subtractYOffset(1 + (int) fallVelocity);
                    }
                } else {
                    camera.addYOffset(1);
                    isOnGround = true;
                }
            } else {
                if (jumpVelocity > 0) {
                    camera.addYOffset((int) (jumpVelocity / (gravity - 3)));
                    jumpVelocity--;
                } else
                    jumping = false;
            }
            if (!isOnGround)
                fallVelocity += 0.15;
        }
        animator.tickFrames();

        if (renderingInventory != null)
            renderingInventory.tick(handler);
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(getLocX(), getLocZ(), 100, 100);
        g.drawImage(animator.getCurrentAnimation().getCurrentFrame(), getLocX(), getLocZ(), 100, 100, null);
        g.setColor(Color.YELLOW);
        g.drawRect((int) getCollisionBounds().getX(), (int) getCollisionBounds().getY(), getCollisionBounds().width, getCollisionBounds().height);
        g.setColor(Color.BLACK);

        if (renderingInventory != null)
            renderingInventory.paint(g);
    }

    @Override
    public void onInteract(EntityInteractEvent que) {
        if (!(que.getInteractingEntity() instanceof Glowable))
            return;
        Glowable entity = (Glowable) que.getInteractingEntity();
        entity.setGlowing(!entity.isGlowing());
        entity.setColor(new Color(ThreadLocalRandom.current().nextInt(255) + 1, ThreadLocalRandom.current().nextInt(255) + 1, ThreadLocalRandom.current().nextInt(255) + 1));
    }

    @Override
    public Rectangle getCollisionBounds() {
        return new Rectangle(this.getLocX() + 20, this.getLocZ(), this.getWidth() - 45, this.getHeight());
    }

    public Rectangle getMovementCollisionBounds() {
        return new Rectangle(this.getLocX() + 22, this.getLocZ(), this.getWidth() - 43, this.getHeight() - 2);
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getJumpBoost() {
        return jumpBoost;
    }

    @Override
    public float getGravityValue() {
        return gravity;
    }

    public boolean isJumping() {
        return jumping;
    }

    @Override
    public boolean isGrounded() {
        return isOnGround;
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    @Override
    public int getLeftVelocity() {
        return leftVelocity;
    }

    @Override
    public int getRightVelocity() {
        return rightVelocity;
    }

    @Override
    public int getJumpVelocity() {
        return jumpVelocity;
    }

    public void setGravity(boolean val) {
        hasGravity = val;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void openInventory(Inventory inventory) {
        this.renderingInventory = InventoryFactory.openInventory(this, inventory);
    }

    public void closeInventory() {
        this.renderingInventory = null;
    }

    public int[] getVelocityParset() {
        return velocityParset;
    }
}
