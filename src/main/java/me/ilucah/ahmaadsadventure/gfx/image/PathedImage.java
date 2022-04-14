package me.ilucah.ahmaadsadventure.gfx.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class PathedImage {

    // image that represents the coin's position on the board
    private BufferedImage image;
    private String path;

    public PathedImage(String path) {
        // load the assets
    	this.path = path;
        loadImage();
    }

    private void loadImage() {
        try {
            image = javax.imageio.ImageIO.read(getClass().getClassLoader().getResourceAsStream(path));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, int x, int y, ImageObserver observer) {
        g.drawImage(image, x, y, observer);
    }
    
    public BufferedImage getBufferedImage() {
    	return this.image;
    }
	
}